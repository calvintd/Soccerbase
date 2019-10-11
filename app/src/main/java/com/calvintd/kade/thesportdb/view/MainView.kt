package com.calvintd.kade.thesportdb.view

import retrofit2.HttpException

interface MainView {
    fun loadData()
    fun showResponseError(code: Int)
    fun showException(e: HttpException)
}