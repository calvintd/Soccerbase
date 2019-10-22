package com.calvintd.kade.soccerbase.utils

import com.calvintd.kade.soccerbase.database.DatabaseHelper
import com.calvintd.kade.soccerbase.itemmodel.Match
import org.jetbrains.anko.db.*

class MatchDatabaseDetailsOrganizer (private val helper: DatabaseHelper) {
    fun insertDetails (detailsList: List<String?>, tableName: String, matchId: Int) {
        helper.use {
            if (detailsList.isNotEmpty()) {
                for (i in detailsList.indices) {
                    insert(
                        tableName,
                        Match.MATCH_ID to matchId,
                        Match.PLAYER_NAME to detailsList[i]
                    )
                }
            }
        }
    }

    fun selectDetails (match: Match) : Match {
        val detailsLists = mutableListOf(
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

        helper.use {
            val matchId = match.matchId!!

            for (i in Match.DETAILS_TABLES.indices) {
                select(Match.DETAILS_TABLES[i])
                    .whereArgs("(${Match.MATCH_ID} = {matchId})",
                        "matchId" to matchId)
                    .exec {
                        detailsLists[i] = this.parseList(StringParser)
                    }
            }
        }

        return match.apply {
            // home
            this.homeGoalDetails = detailsLists[0]
            this.homeRedCardDetails = detailsLists[1]
            this.homeYellowCardDetails = detailsLists[2]
            this.homeGoalkeeper = detailsLists[3]
            this.homeDefense = detailsLists[4]
            this.homeMidfield = detailsLists[5]
            this.homeForward = detailsLists[6]
            this.homeSubstitutes = detailsLists[7]
            
            // away
            this.awayGoalDetails = detailsLists[8]
            this.awayRedCardDetails = detailsLists[9]
            this.awayYellowCardDetails = detailsLists[10]
            this.awayGoalkeeper = detailsLists[11]
            this.awayDefense = detailsLists[12]
            this.awayMidfield = detailsLists[13]
            this.awayForward = detailsLists[14]
            this.awaySubstitutes = detailsLists[15]
        }
    }

    fun deleteDetails (tableName: String, matchId: Int) {
        helper.use {
            select(tableName)
                .whereArgs("(${Match.MATCH_ID} = {matchId})",
                    "matchId" to matchId)
                .exec {
                    if (count != 0) {
                        delete(tableName,
                            "(${Match.MATCH_ID} = {matchId})",
                            "matchId" to matchId)
                    }
                }
        }
    }
}