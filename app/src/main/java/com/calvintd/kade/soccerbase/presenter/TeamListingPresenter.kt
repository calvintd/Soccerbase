package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.repository.TeamResponseRepository
import com.calvintd.kade.soccerbase.repository.callback.TeamResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.fetchers.FetchTeamsCoroutines
import com.calvintd.kade.soccerbase.utils.test.CoroutineContextProvider
import com.calvintd.kade.soccerbase.view.TeamListingView
import kotlinx.coroutines.*
import retrofit2.Response

class TeamListingPresenter(private val view: TeamListingView, private val repository: TeamResponseRepository,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun loadLeagueTeams(leagueId: Int, leagueName: String) {
        val fetchedTeams = CoroutineScope(Dispatchers.IO).async {
            getFetchedTeams(leagueId)
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (fetchedTeams.await().isEmpty()) {
                view.showNoTeamsFound(leagueName)
            } else {
                view.loadLeagueTeams(fetchedTeams.await(), leagueName)
            }
        }
    }

    suspend fun getFetchedTeams(leagueId: Int): List<Team> {
        return withContext(context.main) {
            var response: TeamResponse? = TeamResponse(listOf())

            repository.getTeamsByLeague(leagueId, object:
                TeamResponseRepositoryCallback<TeamResponse> {
                override fun onTeamDataLoaded(data: TeamResponse?) {
                    response = data
                    view.onTeamDataLoaded(data)
                }

                override fun onTeamDataError(response: Response<TeamResponse>) {
                    view.onTeamDataError(response)
                }
            })

            FetchTeamsCoroutines.getFetchedTeams(response)
        }
    }
}