package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepository
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.CoroutineContextProvider
import com.calvintd.kade.soccerbase.utils.FetchLeaguesCoroutines
import com.calvintd.kade.soccerbase.view.LeagueListingView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class LeagueListingPresenter(private val view: LeagueListingView, private val repository: LeagueResponseRepository,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    private val fetcher = FetchLeaguesCoroutines

    fun loadData() {
        val fetchedMatches = CoroutineScope(Dispatchers.IO).async {
            getFetchedLeagues()
        }

        CoroutineScope(Dispatchers.IO).launch {
            view.loadData(fetchedMatches.await())
        }
    }

    suspend fun getFetchedLeagues(): List<League> {
        return withContext(context.main) {
            var response: LeagueResponse? = LeagueResponse(listOf())

            repository.getSoccerLeagues(object: LeagueResponseRepositoryCallback<LeagueResponse> {
                override fun onDataLoaded(data: LeagueResponse?) {
                    response = data
                    view.onDataLoaded(data)
                }

                override fun onDataError(response: Response<LeagueResponse>) {
                    view.onDataError(response)
                }

            })

            fetcher.getFetchedLeagues(view, response)
        }
    }
}