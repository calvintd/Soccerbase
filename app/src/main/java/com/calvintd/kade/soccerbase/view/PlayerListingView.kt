package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Player
import com.calvintd.kade.soccerbase.itemmodel.PlayerResponse
import com.calvintd.kade.soccerbase.repository.callback.PlayerResponseRepositoryCallback
import okhttp3.ResponseBody

interface PlayerListingView : PlayerResponseRepositoryCallback<PlayerResponse> {
    fun loadTeamPlayers(players: List<Player>, teamName: String)
    fun showNoPlayersFound(teamName: String)
    fun showResponseError(code: Int, responseBody: ResponseBody?)
}