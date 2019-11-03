package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.Player
import com.calvintd.kade.soccerbase.itemmodel.PlayerResponse
import com.calvintd.kade.soccerbase.repository.PlayerResponseRepository
import com.calvintd.kade.soccerbase.repository.callback.PlayerResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.fetchers.FetchPlayersCoroutines
import com.calvintd.kade.soccerbase.utils.test.CoroutineContextProvider
import com.calvintd.kade.soccerbase.view.PlayerListingView
import kotlinx.coroutines.*
import retrofit2.Response

class PlayerListingPresenter(private val view: PlayerListingView, private val repository: PlayerResponseRepository,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun loadTeamPlayers(teamId: Int, teamName: String) {
        val fetchedPlayers = CoroutineScope(Dispatchers.IO).async {
            getFetchedPlayers(teamId)
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (fetchedPlayers.await().isEmpty()) {
                view.showNoPlayersFound(teamName)
            } else {
                view.loadTeamPlayers(fetchedPlayers.await(), teamName)
            }
        }
    }

    suspend fun getFetchedPlayers(teamId: Int): List<Player> {
        return withContext(context.main) {
            var response: PlayerResponse? = PlayerResponse(listOf())

            repository.getTeamPlayers(teamId, object:
            PlayerResponseRepositoryCallback<PlayerResponse> {
                override fun onPlayerDataLoaded(data: PlayerResponse?) {
                    response = data
                    view.onPlayerDataLoaded(data)
                }

                override fun onPlayerDataError(response: Response<PlayerResponse>) {
                    view.onPlayerDataError(response)
                }

            })

            FetchPlayersCoroutines.getFetchedPlayers(response)
        }
    }
}