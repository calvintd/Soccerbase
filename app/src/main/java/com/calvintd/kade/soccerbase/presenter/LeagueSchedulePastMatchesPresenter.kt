package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.utils.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import kotlinx.coroutines.*

class LeagueSchedulePastMatchesPresenter (private val view: LeagueScheduleView) {
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
        return withContext(Dispatchers.Main) {
            val response = instance.getPastLeagueMatches(leagueId)
            fetcher.getFetchedMatchesLeagueSchedule(view, response)
        }
    }
}