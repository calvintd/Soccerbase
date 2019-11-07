package com.calvintd.kade.soccerbase.repository

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.StandingsResponse
import com.calvintd.kade.soccerbase.repository.callback.StandingsResponseRepositoryCallback
import retrofit2.Response
import java.io.EOFException

class StandingsResponseRepository {
    private lateinit var response: Response<StandingsResponse>

    suspend fun getLeagueStandings(leagueId: Int, callback: StandingsResponseRepositoryCallback<StandingsResponse>) {
        var isEmptyResponseBody = false
        val instance = RetrofitInstance.getInstance()
        try {
            response = instance.getLeagueStandings(leagueId)
        } catch (e: EOFException) {
            isEmptyResponseBody = true
        }

        if (isEmptyResponseBody) {
            callback.onStandingsEmptyResponseBody()
        } else {
            if (response.isSuccessful) {
                callback.onStandingsDataLoaded(response.body())
            } else {
                callback.onStandingsDataError(response)
            }
        }
    }
}