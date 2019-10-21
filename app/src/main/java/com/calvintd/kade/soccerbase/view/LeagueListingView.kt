package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.model.LeagueAdapterModel
import okhttp3.ResponseBody
import retrofit2.HttpException

interface LeagueListingView {
    fun loadData(model: LeagueAdapterModel)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}