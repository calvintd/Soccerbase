package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface TeamResponseRepositoryCallback<TeamResponse> {
    fun onTeamDataLoaded(data: TeamResponse?)
    fun onTeamDataError(response: Response<TeamResponse>)
}