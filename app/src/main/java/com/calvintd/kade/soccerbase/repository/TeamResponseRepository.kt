package com.calvintd.kade.soccerbase.repository

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.repository.callback.TeamResponseRepositoryCallback

class TeamResponseRepository {
    private val instance = RetrofitInstance.getInstance()

    suspend fun getTeamDetails(teamId: Int, callback: TeamResponseRepositoryCallback<TeamResponse>) {
        val response = instance.getTeamDetails(teamId)

        if (response.isSuccessful) {
            callback.onTeamDataLoaded(response.body())
        } else {
            callback.onTeamDataError(response)
        }
    }

    suspend fun getTeamsByLeague(leagueId: Int, callback: TeamResponseRepositoryCallback<TeamResponse>) {
        val response = instance.getTeamsByLeague(leagueId)

        if (response.isSuccessful) {
            callback.onTeamDataLoaded(response.body())
        } else {
            callback.onTeamDataError(response)
        }
    }
}