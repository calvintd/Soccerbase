package com.calvintd.kade.soccerbase.utils.fetchers

import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object FetchLeaguesCoroutines {
    suspend fun getFetchedLeagues(data: LeagueResponse?): List<League> {
        return withContext(Dispatchers.Main) {
            val fetchedLeagues = mutableListOf<League>()
            val leagueResponseItems = data!!.leagues
            for (i in leagueResponseItems.indices) {
                val leagueId = leagueResponseItems[i].leagueId
                val name = leagueResponseItems[i].name
                val badge = leagueResponseItems[i].badge
                val description = leagueResponseItems[i].description

                val league = League(leagueId, name, badge, description)

                fetchedLeagues.add(league)
            }
            fetchedLeagues
        }
    }
}