package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.utils.CoroutineContextProvider
import com.calvintd.kade.soccerbase.utils.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchSearchPresenter(private val view: MatchSearchView, private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    private val instance = RetrofitInstance.getInstance()
    private val fetcher = FetchMatchesCoroutines

    fun loadMatchesByQuery(query: String) {
        val fetchedMatches = CoroutineScope(Dispatchers.IO).async {
            getFetchedMatches(query)
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (fetchedMatches.await().isEmpty()) {
                view.showNoResultsFound(query)
            } else {
                view.loadMatchesByQuery(fetchedMatches.await(), query)
            }
        }
    }

    suspend fun getFetchedMatches(query: String): List<Match> {
        return withContext(context.main) {
            val call = instance.getMatchesSearch(query)
            lateinit var responseParam: Response<MatchResponse>

            call.enqueue(object : Callback<MatchResponse> {
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    view.showCallError(t)
                }

                override fun onResponse(
                    call: Call<MatchResponse>,
                    response: Response<MatchResponse>
                ) {
                    responseParam = response
                }
            })

            fetcher.getFetchedMatchesMatchSearch(view, responseParam)
        }
    }
}