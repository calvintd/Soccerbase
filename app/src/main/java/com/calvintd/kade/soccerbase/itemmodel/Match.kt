package com.calvintd.kade.soccerbase.itemmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Match (
    // general data
    var matchId: Int?, var homeTeamId: Int?, var awayTeamId: Int?, var homeName: String?, var awayName: String?, var homeBadge: String?,
    var awayBadge: String?, var homeScore: Int?, var awayScore: Int?, var matchDate: String?, var matchTime: String?,
    
    // home
    var homeGoalDetails: List<String?>, var homeRedCardDetails: List<String?>, var homeYellowCardDetails: List<String?>, var homeGoalkeeper: List<String?>,
    var homeDefense: List<String?>, var homeMidfield: List<String?>, var homeForward: List<String?>, var homeSubstitutes: List<String?>,
    
    // away
    var awayGoalDetails: List<String?>, var awayRedCardDetails: List<String?>, var awayYellowCardDetails: List<String?>, var awayGoalkeeper: List<String?>,
    var awayDefense: List<String?>, var awayMidfield: List<String?>, var awayForward: List<String?>, var awaySubstitutes: List<String?>): Parcelable {

    companion object {
        const val ID = "ID_"

        // favorite matches
        const val TABLE_FAVORITE = "TABLE_FAVORITE"

        // general data
        const val MATCH_ID = "MATCH_ID"
        const val HOME_TEAM_ID = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID = "AWAY_TEAM_ID"
        const val HOME_NAME = "HOME_NAME"
        const val AWAY_NAME = "AWAY_NAME"
        const val HOME_BADGE = "HOME_BADGE"
        const val AWAY_BADGE = "AWAY_BADGE"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val MATCH_DATE = "MATCH_DATE"
        const val MATCH_TIME = "MATCH_TIME"

        // home
        const val HOME_GOAL_DETAILS = "HOME_GOAL_DETAILS"
        const val HOME_RED_CARD_DETAILS = "HOME_RED_CARD_DETAILS"
        const val HOME_YELLOW_CARD_DETAILS = "HOME_YELLOW_CARD_DETAILS"
        const val HOME_GOALKEEPER = "HOME_GOALKEEPER"
        const val HOME_DEFENSE = "HOME_DEFENSE"
        const val HOME_MIDFIELD = "HOME_MIDFIELD"
        const val HOME_FORWARD = "HOME_FORWARD"
        const val HOME_SUBSTITUTES = "HOME_SUBSTITUTES"
        
        // away
        const val AWAY_GOAL_DETAILS = "AWAY_GOAL_DETAILS"
        const val AWAY_RED_CARD_DETAILS = "AWAY_RED_CARD_DETAILS"
        const val AWAY_YELLOW_CARD_DETAILS = "AWAY_YELLOW_CARD_DETAILS"
        const val AWAY_GOALKEEPER = "AWAY_GOALKEEPER"
        const val AWAY_DEFENSE = "AWAY_DEFENSE"
        const val AWAY_MIDFIELD = "AWAY_MIDFIELD"
        const val AWAY_FORWARD = "AWAY_FORWARD"
        const val AWAY_SUBSTITUTES = "AWAY_SUBSTITUTES"

        // home goals
        const val TABLE_HOME_GOALS = "TABLE_HOME_GOALS"

        // home red cards
        const val TABLE_HOME_RED_CARDS = "TABLE_HOME_RED_CARDS"

        // home yellow cards
        const val TABLE_HOME_YELLOW_CARDS = "TABLE_HOME_YELLOW_CARDS"

        // home goalkeeper
        const val TABLE_HOME_GOALKEEPER = "TABLE_HOME_GOALKEEPER"

        // home defense
        const val TABLE_HOME_DEFENSE = "TABLE_HOME_DEFENSE"

        // home midfield
        const val TABLE_HOME_MIDFIELD = "TABLE_HOME_MIDFIELD"

        // home forward
        const val TABLE_HOME_FORWARD = "TABLE_HOME_FORWARD"

        // home substitutes
        const val TABLE_HOME_SUBSTITUTES = "TABLE_HOME_SUBSTITUTES"

        // away goals
        const val TABLE_AWAY_GOALS = "TABLE_AWAY_GOALS"

        // away red cards
        const val TABLE_AWAY_RED_CARDS = "TABLE_AWAY_RED_CARDS"

        // away yellow cards
        const val TABLE_AWAY_YELLOW_CARDS = "TABLE_AWAY_YELLOW_CARDS"

        // away goalkeeper
        const val TABLE_AWAY_GOALKEEPER = "TABLE_AWAY_GOALKEEPER"

        // away defense
        const val TABLE_AWAY_DEFENSE = "TABLE_AWAY_DEFENSE"

        // away midfield
        const val TABLE_AWAY_MIDFIELD = "TABLE_AWAY_MIDFIELD"

        // away forward
        const val TABLE_AWAY_FORWARD = "TABLE_AWAY_FORWARD"

        // away substitutes
        const val TABLE_AWAY_SUBSTITUTES = "TABLE_AWAY_SUBSTITUTES"

        // details player name column
        const val PLAYER_NAME = "PLAYER_NAME"
    }
}