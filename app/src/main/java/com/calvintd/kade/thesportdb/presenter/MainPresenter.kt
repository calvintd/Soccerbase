package com.calvintd.kade.thesportdb.presenter

import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.thesportdb.api.RetrofitInstance
import com.calvintd.kade.thesportdb.model.League
import com.calvintd.kade.thesportdb.recyclerview.LeagueAdapter
import com.calvintd.kade.thesportdb.view.MainView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainPresenter(private val view: MainView) {
    fun loadData(recyclerView: RecyclerView?) {
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

                        recyclerView?.adapter = LeagueAdapter(leagues)
                        view.loadData()
                    }
                    else {
                        view.showResponseError(response.code())
                    }
                } catch (e: HttpException) {
                    view.showException(e)
                }
            }
        }
    }
}