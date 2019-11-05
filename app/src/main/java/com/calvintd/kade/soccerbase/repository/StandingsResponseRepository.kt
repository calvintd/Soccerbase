package com.calvintd.kade.soccerbase.repository

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.StandingsResponse
import com.calvintd.kade.soccerbase.repository.callback.StandingsResponseRepositoryCallback

class StandingsResponseRepository {
    suspend fun getLeagueStandings(leagueId: Int, callback: StandingsResponseRepositoryCallback<StandingsResponse>) {
        val instance = RetrofitInstance.getInstance()
        val response = instance.getLeagueStandings(leagueId)

        if (response.isSuccessful) {
            callback.onStandingsDataLoaded(response.body())
        } else {
            callback.onStandingsDataError(response)
        }
    }
}