package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Match
import okhttp3.ResponseBody
import retrofit2.HttpException

interface MatchSearchView {
    fun loadMatchesByQuery(matches: List<Match>, query: String)
    fun showNoResultsFound(query: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}