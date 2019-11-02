package com.calvintd.kade.soccerbase.repository

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.repository.callback.MatchResponseRepositoryCallback

class MatchResponseRepository {
    suspend fun getMatchesSearch(query: String, callback: MatchResponseRepositoryCallback<MatchResponse>) {
        val instance = RetrofitInstance.getInstance()
        val response = instance.getMatchesByTeamName(query)

        if (response.isSuccessful) {
            callback.onMatchDataLoaded(response.body())
        } else {
            callback.onMatchDataError(response)
        }
    }
}