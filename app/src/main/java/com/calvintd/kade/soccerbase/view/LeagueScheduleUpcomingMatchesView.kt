package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Match
import okhttp3.ResponseBody
import retrofit2.HttpException

interface LeagueScheduleUpcomingMatchesView {
    fun loadMatchesByLeague(matches: ArrayList<Match>, league: String)
    fun showNoResultsFound(league: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}