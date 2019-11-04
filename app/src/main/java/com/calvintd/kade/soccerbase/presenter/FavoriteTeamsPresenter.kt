package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.database.DatabaseHelper
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.view.FavoriteTeamsView

class FavoriteTeamsPresenter(private val view: FavoriteTeamsView) {
    val teams = mutableListOf<Team>()

    fun loadFavorites(helper: DatabaseHelper) {
        teams.clear()

    }
}