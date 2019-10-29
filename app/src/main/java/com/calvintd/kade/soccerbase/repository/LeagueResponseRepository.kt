package com.calvintd.kade.soccerbase.repository

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse

class LeagueResponseRepository {
    private val instance = RetrofitInstance.getInstance()

    suspend fun getSoccerLeagues(callback: LeagueResponseRepositoryCallback<LeagueResponse>) {
        val response = instance.getSoccerLeagues()

        if (response.isSuccessful) {
            callback.onDataLoaded(response.body())
        } else {
            callback.onDataError(response)
        }
    }
}