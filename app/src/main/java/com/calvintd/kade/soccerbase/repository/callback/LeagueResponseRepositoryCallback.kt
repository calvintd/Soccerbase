package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface LeagueResponseRepositoryCallback<LeagueResponse> {
    fun onDataLoaded(data: LeagueResponse?)
    fun onDataError(response: Response<LeagueResponse>)
}