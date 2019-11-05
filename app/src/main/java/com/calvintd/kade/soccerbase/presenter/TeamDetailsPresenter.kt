package com.calvintd.kade.soccerbase.presenter

import android.database.sqlite.SQLiteConstraintException
import com.calvintd.kade.soccerbase.database.DatabaseHelper
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.view.ItemDetailsView
import org.jetbrains.anko.db.*

class TeamDetailsPresenter (private val view: ItemDetailsView) {
    fun checkFavorite (isFavorited: Boolean) {
        view.checkedFavorite(isFavorited)
    }

    fun returnFavorite (teamId: Int, helper: DatabaseHelper): Boolean {
        var isFavorited = false

        try {
            helper.use {
                select(Team.TABLE_FAVORITE)
                    .whereArgs("(${Team.TEAM_ID} = {teamId})",
                        "teamId" to teamId)
                    .exec {
                        isFavorited = count != 0
                    }
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e.localizedMessage)
        }

        return isFavorited
    }

    fun addToFavorites (team: Team, helper: DatabaseHelper) {
        try {
            helper.use {
                insert(Team.TABLE_FAVORITE,
                    Team.TEAM_ID to team.teamId,
                    Team.TEAM_NAME to team.teamName,
                    Team.TEAM_YEAR_FORMED to team.teamYearFormed,
                    Team.TEAM_STADIUM to team.teamStadium,
                    Team.TEAM_STADIUM_LOCATION to team.teamStadiumLocation,
                    Team.TEAM_STADIUM_CAPACITY to team.teamStadiumCapacity,
                    Team.TEAM_WEBSITE to team.teamWebsite,
                    Team.TEAM_FACEBOOK to team.teamFacebook,
                    Team.TEAM_TWITTER to team.teamTwitter,
                    Team.TEAM_INSTAGRAM to team.teamInstagram,
                    Team.TEAM_YOUTUBE to team.teamYoutube,
                    Team.TEAM_DESCRIPTION to team.teamDescription,
                    Team.TEAM_BANNER to team.teamBanner,
                    Team.TEAM_BADGE to team.teamBadge
                )

                view.addedToFavorites()
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e.localizedMessage)
        }
    }

    fun removeFromFavorites (teamId: Int, helper: DatabaseHelper) {
        try {
            helper.use {
                delete(Team.TABLE_FAVORITE,
                    "(${Team.TEAM_ID} = {teamId})",
                    "teamId" to teamId)

                view.removedFromFavorites()
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e.localizedMessage)
        }
    }
}