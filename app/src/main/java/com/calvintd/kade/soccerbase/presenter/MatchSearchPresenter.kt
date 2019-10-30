package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.repository.MatchResponseRepository
import com.calvintd.kade.soccerbase.repository.MatchResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.CoroutineContextProvider
import com.calvintd.kade.soccerbase.utils.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.*
import retrofit2.Response

class MatchSearchPresenter(private val view: MatchSearchView, private val repository: MatchResponseRepository,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {
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
            var response: MatchResponse? = MatchResponse(listOf())

            repository.getMatchesSearch(query, object: MatchResponseRepositoryCallback<MatchResponse> {
                override fun onDataLoaded(data: MatchResponse?) {
                    response = data
                    view.onDataLoaded(data)
                }

                override fun onDataError(response: Response<MatchResponse>) {
                    view.onDataError(response)
                }
            })

            fetcher.getFetchedMatchesMatchSearch(view, response)
        }
    }
}