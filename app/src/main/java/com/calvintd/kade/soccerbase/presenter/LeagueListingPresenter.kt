package com.calvintd.kade.soccerbase.presenter

import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.adapter.LeagueAdapter
import com.calvintd.kade.soccerbase.model.LeagueAdapterModel
import com.calvintd.kade.soccerbase.view.LeagueListingView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class LeagueListingPresenter(private val view: LeagueListingView) {
    fun loadData() {
        val leagues = ArrayList<League>()
        val instance = RetrofitInstance.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            val response = instance.getSoccerLeagues()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val leagueResponse = response.body()
                        val leagueResponseItems = leagueResponse!!.leagues
                        for (i in leagueResponseItems.indices) {
                            val leagueId = leagueResponseItems[i].leagueId
                            val name = leagueResponseItems[i].name
                            val badge = leagueResponseItems[i].badge
                            val description = leagueResponseItems[i].description

                            val league = League(leagueId, name, badge, description)

                            leagues.add(league)
                        }

                        val leagueAdapter = LeagueAdapter(leagues)
                        val leagueAdapterModel = LeagueAdapterModel(leagueAdapter)
                        view.loadData(leagueAdapterModel)
                    }
                    else {
                        view.showResponseError(response.code(), response.errorBody())
                    }
                } catch (e: HttpException) {
                    view.showException(e)
                }
            }
        }
    }
}