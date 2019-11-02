package com.calvintd.kade.soccerbase.repository

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.repository.callback.LeagueResponseRepositoryCallback

class LeagueResponseRepository {
    suspend fun getSoccerLeagues(callback: LeagueResponseRepositoryCallback<LeagueResponse>) {
        val instance = RetrofitInstance.getInstance()
        val response = instance.getSoccerLeagues()

        if (response.isSuccessful) {
            callback.onDataLoaded(response.body())
        } else {
            callback.onDataError(response)
        }
    }
}