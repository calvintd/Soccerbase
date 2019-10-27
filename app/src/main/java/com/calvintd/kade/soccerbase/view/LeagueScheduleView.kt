package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Match
import okhttp3.ResponseBody
import retrofit2.HttpException

interface LeagueScheduleView {
    fun loadMatchesByLeague(matches: List<Match>, league: String)
    fun showNoResultsFound(league: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}