package com.calvintd.kade.soccerbase.utils.fetchers

import com.calvintd.kade.soccerbase.itemmodel.Standings
import com.calvintd.kade.soccerbase.itemmodel.StandingsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object FetchStandingsCoroutines {
    suspend fun getFetchedStandings(data: StandingsResponse?): List<Standings> {
        return withContext(Dispatchers.Main) {
            val fetchedStandings = mutableListOf<Standings>()

            if (data?.teams != null) {
                val standingsResponseItems = data.teams
                for (i in standingsResponseItems.indices) {
                    val item = standingsResponseItems[i]

                    val name = item.name
                    val matches = item.matches
                    val goalsFor = item.goalsFor
                    val goalsAgainst = item.goalsAgainst
                    val goalsDifference = item.goalsDifference
                    val wins = item.wins
                    val draws = item.draws
                    val losses = item.losses
                    val points = item.points

                    val standings = Standings(
                        name, matches, goalsFor, goalsAgainst, goalsDifference, wins, draws, losses, points
                    )

                    fetchedStandings.add(standings)
                }
            }

            fetchedStandings
        }
    }
}