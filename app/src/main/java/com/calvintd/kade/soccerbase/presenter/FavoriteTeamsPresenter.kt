package com.calvintd.kade.soccerbase.presenter

import android.database.sqlite.SQLiteConstraintException
import com.calvintd.kade.soccerbase.database.DatabaseHelper
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.view.FavoriteTeamsView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavoriteTeamsPresenter(private val view: FavoriteTeamsView) {
    val teams = mutableListOf<Team>()

    fun loadFavorites(helper: DatabaseHelper) {
        teams.clear()

        try {
            helper.use {
                select(Team.TABLE_FAVORITE)
                    .exec {
                        if (count == 0) {
                            view.showNoFavoriteTeams()
                        } else {
                            val parsedTeamsList = parseList(classParser<Team>())
                            teams.addAll(parsedTeamsList)
                        }
                    }
                view.loadFavoriteTeams(teams)
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e.localizedMessage)
        }
    }
}