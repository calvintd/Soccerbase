package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.repository.callback.LeagueResponseRepositoryCallback
import com.calvintd.kade.soccerbase.repository.callback.TeamResponseRepositoryCallback
import okhttp3.ResponseBody

interface TeamListingView : LeagueResponseRepositoryCallback<LeagueResponse>, TeamResponseRepositoryCallback<TeamResponse> {
    fun loadDataIntoSpinner(leagues: List<League>)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}