package com.calvintd.kade.soccerbase.utils

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

object FetchMatchesCoroutines {
    suspend fun getFetchedMatchesMatchSearch(view: MatchSearchView, response: Response<MatchResponse>): List<Match> {
        return withContext(Dispatchers.Main) {
            val fetchedMatches = mutableListOf<Match>()
            val instance = RetrofitInstance.getInstance()
            val processor = MatchDataProcessor

            try {
                if (response.isSuccessful) {
                    val matchResponse = response.body()
                    if (matchResponse?.matches != null) {
                        val matchResponseItems = matchResponse.matches
                        for (i in matchResponseItems.indices) {
                            if (matchResponseItems[i].sport.equals("Soccer")) {
                                val match = matchResponseItems[i]

                                var matchItem = processor.matchDataInit(match)
                                val homeTeamId = matchItem.homeTeamId
                                val awayTeamId = matchItem.awayTeamId

                                // fetch team data
                                CoroutineScope(Dispatchers.IO).launch {
                                    val hResponse = instance.getTeamDetails(homeTeamId)
                                    val aResponse = instance.getTeamDetails(awayTeamId)
                                    withContext(Dispatchers.Main) {
                                        try {
                                            if (hResponse.isSuccessful) {
                                                val homeResponse = hResponse.body()
                                                val awayResponse = aResponse.body()

                                                val homeResponseItems = homeResponse?.teams
                                                val awayResponseItems = awayResponse?.teams

                                                matchItem = processor.assignBadge(matchItem, homeResponseItems, awayResponseItems)

                                                fetchedMatches.add(matchItem)
                                            } else {
                                                view.showResponseError(hResponse.code(), hResponse.errorBody())
                                            }
                                        } catch (e: HttpException) {
                                            view.showException(e)
                                        }
                                    }
                                }.join()
                            }
                        }
                    }
                } else {
                    view.showResponseError(response.code(), response.errorBody())
                }
            } catch (e: HttpException) {
                view.showException(e)
            }
            fetchedMatches
        }
    }

    suspend fun getFetchedMatchesLeagueSchedule(view: LeagueScheduleView, response: Response<MatchLeagueResponse>): List<Match> {
        return withContext(Dispatchers.Main) {
            val fetchedMatches = mutableListOf<Match>()
            val instance = RetrofitInstance.getInstance()
            val processor = MatchDataProcessor

            try {
                if (response.isSuccessful) {
                    val matchResponse = response.body()
                    if (matchResponse?.matches != null) {
                        val matchResponseItems = matchResponse.matches
                        for (i in matchResponseItems.indices) {
                            if (matchResponseItems[i].sport.equals("Soccer")) {
                                val match = matchResponseItems[i]

                                var matchItem = processor.matchDataInit(match)
                                val homeTeamId = matchItem.homeTeamId
                                val awayTeamId = matchItem.awayTeamId

                                // fetch team data
                                // fetch team data
                                CoroutineScope(Dispatchers.IO).launch {
                                    val hResponse = instance.getTeamDetails(homeTeamId)
                                    val aResponse = instance.getTeamDetails(awayTeamId)
                                    withContext(Dispatchers.Main) {
                                        try {
                                            if (hResponse.isSuccessful) {
                                                val homeResponse = hResponse.body()
                                                val awayResponse = aResponse.body()

                                                val homeResponseItems = homeResponse?.teams
                                                val awayResponseItems = awayResponse?.teams

                                                matchItem = processor.assignBadge(matchItem, homeResponseItems, awayResponseItems)

                                                fetchedMatches.add(matchItem)
                                            } else {
                                                view.showResponseError(hResponse.code(), hResponse.errorBody())
                                            }
                                        } catch (e: HttpException) {
                                            view.showException(e)
                                        }
                                    }
                                }.join()
                            }
                        }
                    }
                } else {
                    view.showResponseError(response.code(), response.errorBody())
                }
            } catch (e: HttpException) {
                view.showException(e)
            }
            fetchedMatches
        }
    }
}