package com.calvintd.kade.soccerbase.presenter

import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.adapter.MatchAdapter
import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.model.MatchAdapterModel
import com.calvintd.kade.soccerbase.utils.MatchDataProcessor
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MatchSearchPresenter(private val view: MatchSearchView) {
    fun loadMatchesByQuery(query: String) {
        val matches = ArrayList<Match>()
        val instance = RetrofitInstance.getInstance()
        val processor = MatchDataProcessor

        CoroutineScope(Dispatchers.IO).launch {
            val response = instance.getMatchesSearch(query)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val matchResponse = response.body()
                        if (matchResponse?.matches == null) {
                            view.showNoResultsFound(query)
                        } else {
                            val matchResponseItems = matchResponse.matches
                            for (i in matchResponseItems.indices) {
                                if (matchResponseItems[i].sport.equals("Soccer")) {
                                    val match = matchResponseItems[i]

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
                                                        val matchAdapter = MatchAdapter(matches)
                                                        val matchAdapterModel = MatchAdapterModel(matchAdapter)
                                                        view.loadMatchesByQuery(matchAdapterModel, query)
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