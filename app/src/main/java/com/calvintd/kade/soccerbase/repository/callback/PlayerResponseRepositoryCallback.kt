package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface PlayerResponseRepositoryCallback<PlayerResponse> {
    fun onPlayerDataLoaded(data: PlayerResponse?)
    fun onPlayerDataError(response: Response<PlayerResponse>)
}