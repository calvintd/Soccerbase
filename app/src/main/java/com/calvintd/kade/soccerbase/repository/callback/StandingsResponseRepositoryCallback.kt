package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface StandingsResponseRepositoryCallback<StandingsResponse> {
    fun onStandingsEmptyResponseBody()
    fun onStandingsDataLoaded(data: StandingsResponse?)
    fun onStandingsDataError(response: Response<StandingsResponse>)
}