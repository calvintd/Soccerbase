package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.repository.MatchResponseRepository
import com.calvintd.kade.soccerbase.repository.callback.MatchResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.test.CoroutineContextProvider
import com.calvintd.kade.soccerbase.utils.fetchers.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.*
import retrofit2.Response

class MatchSearchPresenter(private val view: MatchSearchView, private val repository: MatchResponseRepository,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
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
                override fun onMatchDataLoaded(data: MatchResponse?) {
                    response = data
                    view.onMatchDataLoaded(data)
                }

                override fun onMatchDataError(response: Response<MatchResponse>) {
                    view.onMatchDataError(response)
                }
            })

            FetchMatchesCoroutines.getFetchedMatchesMatchSearch(view, response)
        }
    }
}