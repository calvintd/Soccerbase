package com.calvintd.kade.soccerbase.presenter

import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.adapter.MatchAdapter
import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.model.Match
import com.calvintd.kade.soccerbase.utils.MatchDataProcessing
import com.calvintd.kade.soccerbase.view.LeagueSchedulePastMatchesView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class LeagueSchedulePastMatchesPresenter (private val view: LeagueSchedulePastMatchesView) {
    fun loadMatchesByLeague (recyclerView: RecyclerView?, leagueId: Int, leagueName: String) {
        val matches = ArrayList<Match>()
        val instance = RetrofitInstance.getInstance()
        val processor = MatchDataProcessing

        CoroutineScope(Dispatchers.IO).launch {
            val response = instance.getPastLeagueMatches(leagueId)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val matchResponse = response.body()
                        if (matchResponse?.matches == null) {
                            view.showNoResultsFound(leagueName)
                        } else {
                            val matchResponseItems = matchResponse.matches
                            for (i in matchResponseItems.indices) {
                                if (matchResponseItems[i].sport.equals("Soccer")) {
                                    val match  = matchResponseItems[i]

                                    var matchItem = processor.matchDataInit(match)
                                    val homeTeamId = matchItem.homeTeamId
                                    val awayTeamId = matchItem.awayTeamId

                                    // fetch team data
                                    CoroutineScope(Dispatchers.IO).launch {
                                        val hResponse = instance.getTeamDetails(homeTeamId!!.toInt())
                                        val aResponse = instance.getTeamDetails(awayTeamId!!.toInt())
                                        withContext(Dispatchers.Main) {
                                            try {
                                                if (response.isSuccessful) {
                                                    val homeResponse = hResponse.body()
                                                    val awayResponse = aResponse.body()

                                                    val homeResponseItems = homeResponse!!.teams
                                                    val awayResponseItems = awayResponse!!.teams

                                                    matchItem = processor.assignBadge(matchItem, homeResponseItems, awayResponseItems)

                                                    matches.add(matchItem)

                                                    if (i == matchResponseItems.lastIndex) {
                                                        recyclerView?.adapter = MatchAdapter(matches)
                                                        view.loadMatchesByLeague(leagueName)
                                                    }
                                                } else {
                                                    view.showResponseError(hResponse.code(), hResponse.errorBody())
                                                }
                                            } catch (e: HttpException) {
                                                view.showException(e)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        view.showResponseError(response.code(), response.errorBody())
                    }
                } catch (e: HttpException) {
                    view.showException(e)
                }
            }
        }
    }
}