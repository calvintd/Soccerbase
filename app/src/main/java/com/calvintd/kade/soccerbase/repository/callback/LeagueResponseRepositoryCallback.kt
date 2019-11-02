package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface LeagueResponseRepositoryCallback<LeagueResponse> {
    fun onLeagueDataLoaded(data: LeagueResponse?)
    fun onLeagueDataError(response: Response<LeagueResponse>)
}