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

                // home
                if (match.homeGoalDetails.isNotEmpty()) {
                    for (i in match.homeGoalDetails.indices) {
                        insert(
                            Match.TABLE_HOME_GOALS,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.homeGoalDetails[i]
                        )
                    }
                }

                if (match.homeRedCardDetails.isNotEmpty()) {
                    for (i in match.homeRedCardDetails.indices) {
                        insert(
                            Match.TABLE_HOME_RED_CARDS,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.homeRedCardDetails[i]
                        )
                    }
                }

                if (match.homeYellowCardDetails.isNotEmpty()) {
                    for (i in match.homeYellowCardDetails.indices) {
                        insert(
                            Match.TABLE_HOME_YELLOW_CARDS,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.homeYellowCardDetails[i]
                        )
                    }
                }

                if (match.homeGoalkeeper.isNotEmpty()) {
                    for (i in match.homeGoalkeeper.indices) {
                        insert(
                            Match.TABLE_HOME_GOALKEEPER,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.homeGoalkeeper[i]
                        )
                    }
                }

                if (match.homeDefense.isNotEmpty()) {
                    for (i in match.homeDefense.indices) {
                        insert(
                            Match.TABLE_HOME_DEFENSE,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.homeDefense[i]
                        )
                    }
                }

                if (match.homeMidfield.isNotEmpty()) {
                    for (i in match.homeMidfield.indices) {
                        insert(
                            Match.TABLE_HOME_MIDFIELD,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.homeMidfield[i]
                        )
                    }
                }

                if (match.homeForward.isNotEmpty()) {
                    for (i in match.homeForward.indices) {
                        insert(
                            Match.TABLE_HOME_FORWARD,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.homeForward[i]
                        )
                    }
                }

                if (match.homeSubstitutes.isNotEmpty()) {
                    for (i in match.homeSubstitutes.indices) {
                        insert(
                            Match.TABLE_HOME_SUBSTITUTES,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.homeSubstitutes[i]
                        )
                    }
                }

                // away
                if (match.awayGoalDetails.isNotEmpty()) {
                    for (i in match.awayGoalDetails.indices) {
                        insert(
                            Match.TABLE_AWAY_GOALS,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.awayGoalDetails[i]
                        )
                    }
                }

                if (match.awayRedCardDetails.isNotEmpty()) {
                    for (i in match.awayRedCardDetails.indices) {
                        insert(
                            Match.TABLE_AWAY_RED_CARDS,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.awayRedCardDetails[i]
                        )
                    }
                }

                if (match.awayYellowCardDetails.isNotEmpty()) {
                    for (i in match.awayYellowCardDetails.indices) {
                        insert(
                            Match.TABLE_AWAY_YELLOW_CARDS,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.awayYellowCardDetails[i]
                        )
                    }
                }

                if (match.awayGoalkeeper.isNotEmpty()) {
                    for (i in match.awayGoalkeeper.indices) {
                        insert(
                            Match.TABLE_AWAY_GOALKEEPER,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.awayGoalkeeper[i]
                        )
                    }
                }

                if (match.awayDefense.isNotEmpty()) {
                    for (i in match.awayDefense.indices) {
                        insert(
                            Match.TABLE_AWAY_DEFENSE,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.awayDefense[i]
                        )
                    }
                }

                if (match.awayMidfield.isNotEmpty()) {
                    for (i in match.awayMidfield.indices) {
                        insert(
                            Match.TABLE_AWAY_MIDFIELD,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.awayMidfield[i]
                        )
                    }
                }

                if (match.awayForward.isNotEmpty()) {
                    for (i in match.awayForward.indices) {
                        insert(
                            Match.TABLE_AWAY_FORWARD,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.awayForward[i]
                        )
                    }
                }

                if (match.awaySubstitutes.isNotEmpty()) {
                    for (i in match.awaySubstitutes.indices) {
                        insert(
                            Match.TABLE_AWAY_SUBSTITUTES,
                            Match.MATCH_ID to matchId,
                            Match.PLAYER_NAME to match.awaySubstitutes[i]
                        )
                    }
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