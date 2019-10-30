package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.repository.MatchResponseRepositoryCallback
import okhttp3.ResponseBody
import retrofit2.HttpException

interface MatchSearchView : MatchResponseRepositoryCallback<MatchResponse> {
    fun loadMatchesByQuery(matches: List<Match>, query: String)
    fun showNoResultsFound(query: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}