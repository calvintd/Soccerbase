package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.repository.callback.MatchLeagueResponseRepositoryCallback
import okhttp3.ResponseBody

interface LeagueScheduleView :
    MatchLeagueResponseRepositoryCallback<MatchLeagueResponse> {
    fun loadMatchesByLeague(matches: List<Match>, league: String)
    fun showNoResultsFound(league: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}