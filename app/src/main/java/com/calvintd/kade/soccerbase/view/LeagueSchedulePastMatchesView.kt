package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.model.MatchAdapterModel
import okhttp3.ResponseBody
import retrofit2.HttpException

interface LeagueSchedulePastMatchesView {
    fun loadMatchesByLeague(model: MatchAdapterModel, league: String)
    fun showNoResultsFound(league: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
    fun showException(e: HttpException)
}