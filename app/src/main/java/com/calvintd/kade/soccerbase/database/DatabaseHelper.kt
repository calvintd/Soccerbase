package com.calvintd.kade.soccerbase.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.Match
import org.jetbrains.anko.db.*

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context,
    context.resources.getString(R.string.database_helper_db_name), null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Match.TABLE_FAVORITE,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

            // general data
            Match.MATCH_ID to INTEGER,
            Match.HOME_TEAM_ID to INTEGER,
            Match.AWAY_TEAM_ID to INTEGER,
            Match.HOME_NAME to TEXT,
            Match.AWAY_NAME to TEXT,
            Match.HOME_BADGE to BLOB,
            Match.AWAY_BADGE to BLOB,
            Match.HOME_SCORE to INTEGER,
            Match.AWAY_SCORE to INTEGER,
            Match.MATCH_DATE to TEXT,
            Match.MATCH_TIME to TEXT,

            // home
            Match.HOME_GOAL_DETAILS to INTEGER,
            Match.HOME_RED_CARD_DETAILS to INTEGER,
            Match.HOME_YELLOW_CARD_DETAILS to INTEGER,
            Match.HOME_GOALKEEPER to INTEGER,
            Match.HOME_DEFENSE to INTEGER,
            Match.HOME_MIDFIELD to INTEGER,
            Match.HOME_FORWARD to INTEGER,
            Match.HOME_SUBSTITUTES to INTEGER,

            // away
            Match.AWAY_GOAL_DETAILS to INTEGER,
            Match.AWAY_RED_CARD_DETAILS to INTEGER,
            Match.AWAY_YELLOW_CARD_DETAILS to INTEGER,
            Match.AWAY_GOALKEEPER to INTEGER,
            Match.AWAY_DEFENSE to INTEGER,
            Match.AWAY_MIDFIELD to INTEGER,
            Match.AWAY_FORWARD to INTEGER,
            Match.AWAY_SUBSTITUTES to INTEGER,

            // foreign keys
            FOREIGN_KEY(Match.HOME_GOAL_DETAILS, Match.TABLE_HOME_GOALS, Match.ID),
            FOREIGN_KEY(Match.HOME_RED_CARD_DETAILS, Match.TABLE_HOME_RED_CARDS, Match.ID),
            FOREIGN_KEY(Match.HOME_YELLOW_CARD_DETAILS, Match.TABLE_HOME_YELLOW_CARDS, Match.ID),
            FOREIGN_KEY(Match.HOME_GOALKEEPER, Match.TABLE_HOME_GOALKEEPER, Match.ID),
            FOREIGN_KEY(Match.HOME_DEFENSE, Match.TABLE_HOME_DEFENSE, Match.ID),
            FOREIGN_KEY(Match.HOME_MIDFIELD, Match.TABLE_HOME_MIDFIELD, Match.ID),
            FOREIGN_KEY(Match.HOME_FORWARD, Match.TABLE_HOME_FORWARD, Match.ID),
            FOREIGN_KEY(Match.HOME_SUBSTITUTES, Match.TABLE_HOME_SUBSTITUTES, Match.ID),
            FOREIGN_KEY(Match.AWAY_GOAL_DETAILS, Match.TABLE_AWAY_GOALS, Match.ID),
            FOREIGN_KEY(Match.AWAY_RED_CARD_DETAILS, Match.TABLE_AWAY_RED_CARDS, Match.ID),
            FOREIGN_KEY(Match.AWAY_YELLOW_CARD_DETAILS, Match.TABLE_AWAY_YELLOW_CARDS, Match.ID),
            FOREIGN_KEY(Match.AWAY_GOALKEEPER, Match.TABLE_AWAY_GOALKEEPER, Match.ID),
            FOREIGN_KEY(Match.AWAY_DEFENSE, Match.TABLE_AWAY_DEFENSE, Match.ID),
            FOREIGN_KEY(Match.AWAY_MIDFIELD, Match.TABLE_AWAY_MIDFIELD, Match.ID),
            FOREIGN_KEY(Match.AWAY_FORWARD, Match.TABLE_AWAY_FORWARD, Match.ID),
            FOREIGN_KEY(Match.AWAY_SUBSTITUTES, Match.TABLE_AWAY_SUBSTITUTES, Match.ID)            
        )

        // home details
        db.createTable(Match.TABLE_HOME_GOALS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_HOME_RED_CARDS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_HOME_YELLOW_CARDS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_HOME_GOALKEEPER,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_HOME_DEFENSE,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_HOME_MIDFIELD,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_HOME_FORWARD,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_HOME_SUBSTITUTES,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )

        // away details
        db.createTable(Match.TABLE_AWAY_GOALS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_AWAY_RED_CARDS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_AWAY_YELLOW_CARDS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_AWAY_GOALKEEPER,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_AWAY_DEFENSE,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_AWAY_MIDFIELD,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_AWAY_FORWARD,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
        db.createTable(Match.TABLE_AWAY_SUBSTITUTES,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Match.TABLE_FAVORITE, true)
        db.dropTable(Match.TABLE_HOME_GOALS, true)
        db.dropTable(Match.TABLE_HOME_RED_CARDS, true)
        db.dropTable(Match.TABLE_HOME_YELLOW_CARDS, true)
        db.dropTable(Match.TABLE_HOME_GOALKEEPER, true)
        db.dropTable(Match.TABLE_HOME_DEFENSE, true)
        db.dropTable(Match.TABLE_HOME_MIDFIELD, true)
        db.dropTable(Match.TABLE_HOME_FORWARD, true)
        db.dropTable(Match.TABLE_HOME_SUBSTITUTES, true)
        db.dropTable(Match.TABLE_AWAY_GOALS, true)
        db.dropTable(Match.TABLE_AWAY_RED_CARDS, true)
        db.dropTable(Match.TABLE_AWAY_YELLOW_CARDS, true)
        db.dropTable(Match.TABLE_AWAY_GOALKEEPER, true)
        db.dropTable(Match.TABLE_AWAY_DEFENSE, true)
        db.dropTable(Match.TABLE_AWAY_MIDFIELD, true)
        db.dropTable(Match.TABLE_AWAY_FORWARD, true)
        db.dropTable(Match.TABLE_AWAY_SUBSTITUTES, true)
    }
}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)