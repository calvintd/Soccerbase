package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.repository.callback.MatchResponseRepositoryCallback
import okhttp3.ResponseBody

interface MatchSearchView :
    MatchResponseRepositoryCallback<MatchResponse> {
    fun loadMatchesByQuery(matches: List<Match>, query: String)
    fun showNoResultsFound(query: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}