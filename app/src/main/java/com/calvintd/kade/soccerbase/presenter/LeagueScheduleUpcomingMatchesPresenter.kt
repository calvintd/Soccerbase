package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.repository.MatchLeagueResponseRepository
import com.calvintd.kade.soccerbase.repository.callback.MatchLeagueResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.test.CoroutineContextProvider
import com.calvintd.kade.soccerbase.utils.fetchers.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import kotlinx.coroutines.*
import retrofit2.Response

class LeagueScheduleUpcomingMatchesPresenter (private val view: LeagueScheduleView, private val repository: MatchLeagueResponseRepository,
                                              private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun loadMatchesByLeague (leagueId: Int, leagueName: String) {
        val fetchedMatches = CoroutineScope(Dispatchers.IO).async {
            getFetchedMatches(leagueId)
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (fetchedMatches.await().isEmpty()) {
                view.showNoResultsFound(leagueName)
            } else {
                view.loadMatchesByLeague(fetchedMatches.await(), leagueName)
            }
        }
    }

    suspend fun getFetchedMatches(leagueId: Int): List<Match> {
        return withContext(context.main) {
            var response: MatchLeagueResponse? = MatchLeagueResponse(listOf())

            repository.getUpcomingLeagueMatches(leagueId, object:
                MatchLeagueResponseRepositoryCallback<MatchLeagueResponse> {
                override fun onMatchLeagueDataLoaded(data: MatchLeagueResponse?) {
                    response = data
                    view.onMatchLeagueDataLoaded(data)
                }

                override fun onMatchLeagueDataError(response: Response<MatchLeagueResponse>) {
                    view.onMatchLeagueDataError(response)
                }
            })

            FetchMatchesCoroutines.getFetchedMatchesLeagueSchedule(view, response)
        }
    }
}