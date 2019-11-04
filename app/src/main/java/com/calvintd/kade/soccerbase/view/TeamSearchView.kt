package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.repository.callback.TeamResponseRepositoryCallback
import okhttp3.ResponseBody

interface TeamSearchView : TeamResponseRepositoryCallback<TeamResponse> {
    fun loadTeamsByQuery(teams: List<Team>, query: String)
    fun showNoResultsFound(query: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}