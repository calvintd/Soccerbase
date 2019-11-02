package com.calvintd.kade.soccerbase.repository.callback

import retrofit2.Response

interface PlayerResponseRepositoryCallback<PlayerResponse> {
    fun onDataLoaded(data: PlayerResponse?)
    fun onDataError(response: Response<PlayerResponse>)
}