package com.calvintd.kade.soccerbase.repository

import retrofit2.Response

interface MatchLeagueResponseRepositoryCallback<MatchLeagueResponse> {
    fun onDataLoaded(data: MatchLeagueResponse?)
    fun onDataError(response: Response<MatchLeagueResponse>)
}