package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface MatchResponseRepositoryCallback<MatchResponse> {
    fun onDataLoaded(data: MatchResponse?)
    fun onDataError(response: Response<MatchResponse>)
}