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
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,

            // general data
            Match.MATCH_ID to INTEGER + UNIQUE,
            Match.HOME_TEAM_ID to INTEGER,
            Match.AWAY_TEAM_ID to INTEGER,
            Match.HOME_NAME to TEXT,
            Match.AWAY_NAME to TEXT,
            Match.HOME_BADGE to TEXT,
            Match.AWAY_BADGE to TEXT,
            Match.HOME_SCORE to INTEGER,
            Match.AWAY_SCORE to INTEGER,
            Match.MATCH_DATE to TEXT,
            Match.MATCH_TIME to TEXT
        )

        // home details
        db.createTable(Match.TABLE_HOME_GOALS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_HOME_RED_CARDS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_HOME_YELLOW_CARDS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_HOME_GOALKEEPER,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_HOME_DEFENSE,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_HOME_MIDFIELD,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_HOME_FORWARD,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_HOME_SUBSTITUTES,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )

        // away details
        db.createTable(Match.TABLE_AWAY_GOALS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_AWAY_RED_CARDS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_AWAY_YELLOW_CARDS,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_AWAY_GOALKEEPER,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_AWAY_DEFENSE,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_AWAY_MIDFIELD,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_AWAY_FORWARD,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
        )
        db.createTable(Match.TABLE_AWAY_SUBSTITUTES,
            true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            Match.MATCH_ID to INTEGER,
            Match.PLAYER_NAME to TEXT,
            FOREIGN_KEY(Match.MATCH_ID, Match.TABLE_FAVORITE, Match.MATCH_ID)
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