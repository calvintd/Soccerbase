package com.calvintd.kade.soccerbase.utils.fetchers

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.utils.matchutils.MatchDataProcessor
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object FetchMatchesCoroutines {
    suspend fun getFetchedMatchesMatchSearch(view: MatchSearchView, data: MatchResponse?): List<Match> {
        return withContext(Dispatchers.Main) {
            val fetchedMatches = mutableListOf<Match>()
            val instance = RetrofitInstance.getInstance()

            if (data?.matches != null) {
                val matchResponseItems = data.matches
                for (i in matchResponseItems.indices) {
                    if (matchResponseItems[i].sport.equals("Soccer")) {
                        val match = matchResponseItems[i]

                        var matchItem =
                            MatchDataProcessor.matchDataInit(
                                match
                            )
                        val homeTeamId = matchItem.homeTeamId
                        val awayTeamId = matchItem.awayTeamId

                        // fetch team data
                        CoroutineScope(Dispatchers.IO).launch {
                            val hResponse = instance.getTeamDetails(homeTeamId)
                            val aResponse = instance.getTeamDetails(awayTeamId)
                            withContext(Dispatchers.Main) {
                                if (hResponse.isSuccessful) {
                                    val homeResponse = hResponse.body()
                                    val awayResponse = aResponse.body()

                                    val homeResponseItems = homeResponse?.teams
                                    val awayResponseItems = awayResponse?.teams

                                    matchItem =
                                        MatchDataProcessor.assignBadge(
                                            matchItem,
                                            homeResponseItems,
                                            awayResponseItems
                                        )

                                    fetchedMatches.add(matchItem)
                                } else {
                                    view.showResponseError(
                                        hResponse.code(),
                                        hResponse.errorBody()
                                    )
                                }
                            }
                        }.join()
                    }
                }
            }
            fetchedMatches
        }
    }

    suspend fun getFetchedMatchesLeagueSchedule(view: LeagueScheduleView, data: MatchLeagueResponse?): List<Match> {
        return withContext(Dispatchers.Main) {
            val fetchedMatches = mutableListOf<Match>()
            val instance = RetrofitInstance.getInstance()
            val processor = MatchDataProcessor

            if (data?.matches != null) {
                val matchLeagueResponseItems = data.matches
                for (i in matchLeagueResponseItems.indices) {
                    if (matchLeagueResponseItems[i].sport.equals("Soccer")) {
                        val match = matchLeagueResponseItems[i]

                        var matchItem =
                            MatchDataProcessor.matchDataInit(
                                match
                            )
                        val homeTeamId = matchItem.homeTeamId
                        val awayTeamId = matchItem.awayTeamId

                        // fetch team data
                        CoroutineScope(Dispatchers.IO).launch {
                            val hResponse = instance.getTeamDetails(homeTeamId)
                            val aResponse = instance.getTeamDetails(awayTeamId)
                            withContext(Dispatchers.Main) {
                                if (hResponse.isSuccessful) {
                                    val homeResponse = hResponse.body()
                                    val awayResponse = aResponse.body()

                                    val homeResponseItems = homeResponse?.teams
                                    val awayResponseItems = awayResponse?.teams

                                    matchItem =
                                        MatchDataProcessor.assignBadge(
                                            matchItem,
                                            homeResponseItems,
                                            awayResponseItems
                                        )

                                    fetchedMatches.add(matchItem)
                                } else {
                                    view.showResponseError(
                                        hResponse.code(),
                                        hResponse.errorBody()
                                    )
                                }
                            }
                        }.join()
                    }
                }
            }
            fetchedMatches
        }
    }
}