package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface MatchLeagueResponseRepositoryCallback<MatchLeagueResponse> {
    fun onMatchLeagueDataLoaded(data: MatchLeagueResponse?)
    fun onMatchLeagueDataError(response: Response<MatchLeagueResponse>)
}