package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.repository.callback.LeagueResponseRepositoryCallback
import com.calvintd.kade.soccerbase.repository.callback.TeamResponseRepositoryCallback
import okhttp3.ResponseBody

interface TeamListingView : TeamResponseRepositoryCallback<TeamResponse> {
    fun loadLeagueTeams(teams: List<Team>, leagueName: String)
    fun showNoTeamsFound(leagueName: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}