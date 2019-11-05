package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Standings
import com.calvintd.kade.soccerbase.itemmodel.StandingsResponse
import com.calvintd.kade.soccerbase.repository.callback.StandingsResponseRepositoryCallback
import okhttp3.ResponseBody

interface LeagueStandingsView : StandingsResponseRepositoryCallback<StandingsResponse> {
    fun loadStandings(standings: List<Standings>, leagueName: String)
    fun showNoStandingsFound(leagueName: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}