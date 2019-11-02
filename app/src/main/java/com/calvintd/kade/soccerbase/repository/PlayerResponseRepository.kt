package com.calvintd.kade.soccerbase.repository

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.PlayerResponse
import com.calvintd.kade.soccerbase.repository.callback.PlayerResponseRepositoryCallback

class PlayerResponseRepository {
    suspend fun getTeamPlayers(teamId: Int, callback: PlayerResponseRepositoryCallback<PlayerResponse>) {
        val instance = RetrofitInstance.getInstance()
        val response = instance.getTeamPlayers(teamId)

        if (response.isSuccessful) {
            callback.onPlayerDataLoaded(response.body())
        } else {
            callback.onPlayerDataError(response)
        }
    }
}