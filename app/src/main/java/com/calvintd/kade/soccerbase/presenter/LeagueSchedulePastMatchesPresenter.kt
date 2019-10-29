package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.repository.MatchLeagueResponseRepository
import com.calvintd.kade.soccerbase.utils.CoroutineContextProvider
import com.calvintd.kade.soccerbase.utils.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import kotlinx.coroutines.*

class LeagueSchedulePastMatchesPresenter (private val view: LeagueScheduleView, private val repository: MatchLeagueResponseRepository,
                                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

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



            FetchMatchesCoroutines.getFetchedMatchesLeagueSchedule(view, response)
        }
    }
}