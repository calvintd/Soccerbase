package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface TeamResponseRepositoryCallback<TeamResponse> {
    fun onDataLoaded(data: TeamResponse?)
    fun onDataError(response: Response<TeamResponse>)
}