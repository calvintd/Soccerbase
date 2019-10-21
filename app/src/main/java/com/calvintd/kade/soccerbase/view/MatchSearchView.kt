package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.model.MatchAdapterModel
import okhttp3.ResponseBody
import retrofit2.HttpException

interface MatchSearchView {
    fun loadMatchesByQuery(model: MatchAdapterModel, query: String)
    fun showNoResultsFound(query: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}