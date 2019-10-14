package com.calvintd.kade.soccerbase.view

import okhttp3.ResponseBody
import retrofit2.HttpException

interface LeagueSchedulePastMatchesView {
    fun loadMatchesByLeague(league: String)
    fun showNoResultsFound(league: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}