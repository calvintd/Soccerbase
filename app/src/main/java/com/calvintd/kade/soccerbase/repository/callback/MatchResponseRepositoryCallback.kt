package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface MatchResponseRepositoryCallback<MatchResponse> {
    fun onMatchDataLoaded(data: MatchResponse?)
    fun onMatchDataError(response: Response<MatchResponse>)
}