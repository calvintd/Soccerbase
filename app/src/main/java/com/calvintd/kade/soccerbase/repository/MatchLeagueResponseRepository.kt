package com.calvintd.kade.soccerbase.repository

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.repository.callback.MatchLeagueResponseRepositoryCallback

class MatchLeagueResponseRepository {
    private val instance = RetrofitInstance.getInstance()

    suspend fun getPastLeagueMatches(leagueId: Int, callback: MatchLeagueResponseRepositoryCallback<MatchLeagueResponse>) {
        val response = instance.getPastLeagueMatches(leagueId)

        if (response.isSuccessful) {
            callback.onMatchLeagueDataLoaded(response.body())
        } else {
            callback.onMatchLeagueDataError(response)
        }
    }

    suspend fun getUpcomingLeagueMatches(leagueId: Int, callback: MatchLeagueResponseRepositoryCallback<MatchLeagueResponse>) {
        val response = instance.getUpcomingLeagueMatches(leagueId)

        if (response.isSuccessful) {
            callback.onMatchLeagueDataLoaded(response.body())
        } else {
            callback.onMatchLeagueDataError(response)
        }
    }
}