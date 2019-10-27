package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.utils.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.*

class MatchSearchPresenter(private val view: MatchSearchView) {
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
        return withContext(Dispatchers.Main) {
            val response = instance.getMatchesSearch(query)
            fetcher.getFetchedMatchesMatchSearch(view, response)
        }
    }
}