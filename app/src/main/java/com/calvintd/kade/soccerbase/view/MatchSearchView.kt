package com.calvintd.kade.soccerbase.view

import okhttp3.ResponseBody
import retrofit2.HttpException

interface MatchSearchView {
    fun loadMatchByQuery(query: String)
    fun showNoResultsFound(query: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}