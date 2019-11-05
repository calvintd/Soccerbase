package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.Standings
import com.calvintd.kade.soccerbase.itemmodel.StandingsResponse
import com.calvintd.kade.soccerbase.repository.StandingsResponseRepository
import com.calvintd.kade.soccerbase.repository.callback.StandingsResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.fetchers.FetchStandingsCoroutines
import com.calvintd.kade.soccerbase.utils.test.CoroutineContextProvider
import com.calvintd.kade.soccerbase.view.LeagueStandingsView
import kotlinx.coroutines.*
import retrofit2.Response

class LeagueStandingsPresenter (private val view: LeagueStandingsView, private val repository: StandingsResponseRepository,
                                private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun loadStandings(leagueId: Int, leagueName: String) {
        val fetchedStandings = CoroutineScope(Dispatchers.IO).async {
            getFetchedStandings(leagueId)
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (fetchedStandings.await().isEmpty()) {
                view.showNoStandingsFound(leagueName)
            } else {
                view.loadStandings(fetchedStandings.await(), leagueName)
            }
        }
    }

    suspend fun getFetchedStandings(leagueId: Int): List<Standings> {
        return withContext(context.main) {
            var response: StandingsResponse? = StandingsResponse(listOf())

            repository.getLeagueStandings(leagueId, object:
                StandingsResponseRepositoryCallback<StandingsResponse> {
                override fun onStandingsDataLoaded(data: StandingsResponse?) {
                    response = data
                    view.onStandingsDataLoaded(data)
                }

                override fun onStandingsDataError(response: Response<StandingsResponse>) {
                    view.onStandingsDataError(response)
                }

            })

            FetchStandingsCoroutines.getFetchedStandings(response)
        }
    }
}