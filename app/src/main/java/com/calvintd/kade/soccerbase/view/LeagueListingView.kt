package com.calvintd.kade.soccerbase.view

import okhttp3.ResponseBody
import retrofit2.HttpException

interface LeagueListingView {
    fun loadData()
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}