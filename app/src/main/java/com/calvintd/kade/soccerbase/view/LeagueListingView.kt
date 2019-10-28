package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.League
import okhttp3.ResponseBody
import retrofit2.HttpException

interface LeagueListingView {
    fun loadData(leagues: List<League>)
    fun showCallError(t: Throwable)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}