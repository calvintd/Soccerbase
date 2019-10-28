package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.utils.CoroutineContextProvider
import com.calvintd.kade.soccerbase.utils.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueScheduleUpcomingMatchesPresenter (private val view: LeagueScheduleView, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    private val instance = RetrofitInstance.getInstance()
    private val fetcher = FetchMatchesCoroutines

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
            lateinit var responseParam: Response<MatchLeagueResponse>

            instance.getUpcomingLeagueMatches(leagueId).enqueue(object : Callback<MatchLeagueResponse> {
                override fun onFailure(call: Call<MatchLeagueResponse>, t: Throwable) {
                    view.showCallError(t)
                }

                override fun onResponse(
                    call: Call<MatchLeagueResponse>,
                    response: Response<MatchLeagueResponse>
                ) {
                    responseParam = response
                }
            })

            fetcher.getFetchedMatchesLeagueSchedule(view, responseParam)
        }
    }
}