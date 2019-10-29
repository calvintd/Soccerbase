package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepositoryCallback
import okhttp3.ResponseBody

interface LeagueListingView : LeagueResponseRepositoryCallback<LeagueResponse> {
    fun loadData(leagues: List<League>)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}