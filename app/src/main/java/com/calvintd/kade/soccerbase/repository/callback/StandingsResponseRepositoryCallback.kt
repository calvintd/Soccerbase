package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface StandingsResponseRepositoryCallback<StandingsResponse> {
    fun onStandingsDataLoaded(data: StandingsResponse?)
    fun onStandingsDataError(response: Response<StandingsResponse>)
}