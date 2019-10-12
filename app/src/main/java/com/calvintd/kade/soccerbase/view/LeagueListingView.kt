package com.calvintd.kade.soccerbase.view

import retrofit2.HttpException

interface LeagueListingView {
    fun loadData()
    fun showResponseError(code: Int)
    fun showException(e: HttpException)
}