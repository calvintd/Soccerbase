package com.calvintd.kade.soccerbase.utils

import com.calvintd.kade.soccerbase.database.DatabaseHelper
import com.calvintd.kade.soccerbase.itemmodel.Match
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

class MatchDatabaseDetailsOrganizer (private val helper: DatabaseHelper) {
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