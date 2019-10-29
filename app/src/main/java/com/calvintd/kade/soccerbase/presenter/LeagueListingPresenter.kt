package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.utils.FetchLeaguesCoroutines
import com.calvintd.kade.soccerbase.view.LeagueListingView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class LeagueListingPresenter(private val view: LeagueListingView) {
    private val instance = RetrofitInstance.getInstance()
    private val fetcher = FetchLeaguesCoroutines

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            view.loadData(getFetchedLeagues())
        }
    }

    suspend fun getFetchedLeagues(): List<League> {
        return withContext(Dispatchers.Main) {
            val response = instance.getSoccerLeagues()
            fetcher.getFetchedLeagues(view, response)
        }
    }
}