package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepository
import com.calvintd.kade.soccerbase.repository.callback.LeagueResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.test.CoroutineContextProvider
import com.calvintd.kade.soccerbase.utils.fetchers.FetchLeaguesCoroutines
import com.calvintd.kade.soccerbase.view.LeagueListingView
import kotlinx.coroutines.*
import retrofit2.Response

class LeagueListingPresenter(private val view: LeagueListingView, private val repository: LeagueResponseRepository,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun loadData() {
        val fetchedLeagues = CoroutineScope(Dispatchers.IO).async {
            getFetchedLeagues()
        }

        CoroutineScope(Dispatchers.IO).launch {
            view.loadData(fetchedLeagues.await())
        }
    }

    suspend fun getFetchedLeagues(): List<League> {
        return withContext(context.main) {
            var response: LeagueResponse? = LeagueResponse(listOf())

            repository.getSoccerLeagues(object:
                LeagueResponseRepositoryCallback<LeagueResponse> {
                override fun onLeagueDataLoaded(data: LeagueResponse?) {
                    response = data
                    view.onLeagueDataLoaded(data)
                }

                override fun onLeagueDataError(response: Response<LeagueResponse>) {
                    view.onLeagueDataError(response)
                }
            })

            FetchLeaguesCoroutines.getFetchedLeagues(response)
        }
    }
}