package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Team

interface FavoriteTeamsView {
    fun loadFavoriteTeams(teams: List<Team>)
    fun showNoFavoriteTeams()
    fun showError(message: String?)
}