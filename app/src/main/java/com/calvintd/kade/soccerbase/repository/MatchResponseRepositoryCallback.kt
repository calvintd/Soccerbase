package com.calvintd.kade.soccerbase.repository

import retrofit2.Response

interface MatchResponseRepositoryCallback<MatchResponse> {
    fun onDataLoaded(data: MatchResponse?)
    fun onDataError(response: Response<MatchResponse>)
}