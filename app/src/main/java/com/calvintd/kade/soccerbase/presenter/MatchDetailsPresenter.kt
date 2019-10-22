package com.calvintd.kade.soccerbase.presenter

import android.database.sqlite.SQLiteConstraintException
import com.calvintd.kade.soccerbase.database.DatabaseHelper
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.utils.MatchDatabaseDetailsOrganizer
import com.calvintd.kade.soccerbase.view.MatchDetailsView
import org.jetbrains.anko.db.*

class MatchDetailsPresenter (private val view: MatchDetailsView) {
    fun checkFavorite (isFavorited: Boolean) {
        view.checkedFavorite(isFavorited)
    }

    fun returnFavorite (matchId: Int, helper: DatabaseHelper): Boolean {
        var isFavorited = false

        try {
            helper.use {
                select(Match.TABLE_FAVORITE)
                    .whereArgs("(${Match.MATCH_ID} = {matchId})",
                        "matchId" to matchId)
                    .exec {
                        isFavorited = count != 0
                    }
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e)
        }

        return isFavorited
    }

    fun addToFavorites (match: Match, helper: DatabaseHelper) {
        try {
            helper.use {
                val organizer = MatchDatabaseDetailsOrganizer(helper)

                val detailsLists = listOf(
                    // home
                    match.homeGoalDetails,
                    match.homeRedCardDetails,
                    match.homeYellowCardDetails,
                    match.homeGoalkeeper,
                    match.homeDefense,
                    match.homeMidfield,
                    match.homeForward,
                    match.homeSubstitutes,
                    
                    // away
                    match.awayGoalDetails,
                    match.awayRedCardDetails,
                    match.awayYellowCardDetails,
                    match.awayGoalkeeper,
                    match.awayDefense,
                    match.awayMidfield,
                    match.awayForward,
                    match.awaySubstitutes
                )
                
                val matchId = match.matchId

                // general data
                insert(Match.TABLE_FAVORITE,
                    Match.MATCH_ID to matchId,
                    Match.HOME_TEAM_ID to match.homeTeamId,
                    Match.AWAY_TEAM_ID to match.awayTeamId,
                    Match.HOME_NAME to match.homeName,
                    Match.AWAY_NAME to match.awayName,
                    Match.HOME_BADGE to match.homeBadge,
                    Match.AWAY_BADGE to match.awayBadge,
                    Match.HOME_SCORE to match.homeScore,
                    Match.AWAY_SCORE to match.awayScore,
                    Match.MATCH_DATE to match.matchDate,
                    Match.MATCH_TIME to match.matchTime
                )

                for (i in Match.DETAILS_TABLES.indices) {
                    organizer.insertDetails(detailsLists[i], Match.DETAILS_TABLES[i], matchId!!)
                }

                view.addedToFavorites()
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e)
        }
    }

    fun removeFromFavorites (matchId: Int, helper: DatabaseHelper) {
        try {
            helper.use {
                val organizer = MatchDatabaseDetailsOrganizer(helper)

                // home
                for (i in Match.DETAILS_TABLES.indices) {
                    organizer.deleteDetails(Match.DETAILS_TABLES[i], matchId)
                }

                // general data
                select(Match.TABLE_FAVORITE)
                    .whereArgs("(${Match.MATCH_ID} = {matchId})",
                        "matchId" to matchId)

                view.removedFromFavorites()
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e)
        }

    }
}