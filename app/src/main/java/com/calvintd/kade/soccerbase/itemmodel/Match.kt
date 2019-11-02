package com.calvintd.kade.soccerbase.itemmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Match (
    // general data
    var id: Int? = null,
    var matchId: Int? = null,
    var homeTeamId: Int? = null,
    var awayTeamId: Int? = null,
    var homeName: String? = null,
    var awayName: String? = null,
    var homeBadge: String? = null,
    var awayBadge: String? = null,
    var homeScore: Int? = null,
    var awayScore: Int? = null,
    var matchDate: String? = null,
    var matchTime: String? = null,
    
    // home
    var homeGoalDetails: List<String?> = listOf(null),
    var homeRedCardDetails: List<String?> = listOf(null),
    var homeYellowCardDetails: List<String?> = listOf(null),
    var homeGoalkeeper: List<String?> = listOf(null),
    var homeDefense: List<String?> = listOf(null),
    var homeMidfield: List<String?> = listOf(null),
    var homeForward: List<String?> = listOf(null),
    var homeSubstitutes: List<String?> = listOf(null),
    
    // away
    var awayGoalDetails: List<String?> = listOf(null),
    var awayRedCardDetails: List<String?> = listOf(null),
    var awayYellowCardDetails: List<String?> = listOf(null),
    var awayGoalkeeper: List<String?> = listOf(null),
    var awayDefense: List<String?> = listOf(null),
    var awayMidfield: List<String?> = listOf(null),
    var awayForward: List<String?> = listOf(null),
    var awaySubstitutes: List<String?> = listOf(null)): Parcelable {

    constructor (
                id: Int?,
                matchId: Int?,
                homeTeamId: Int?,
                awayTeamId: Int?,
                homeName: String?,
                awayName: String?,
                homeBadge: String?,
                awayBadge: String?,
                homeScore: Int?,
                awayScore: Int?,
                matchDate: String?,
                matchTime: String?) : this() {
        this.id = id
        this.matchId = matchId
        this.homeTeamId = homeTeamId
        this.awayTeamId = awayTeamId
        this.homeName = homeName
        this.awayName = awayName
        this.homeBadge = homeBadge
        this.awayBadge = awayBadge
        this.homeScore = homeScore
        this.awayScore = awayScore
        this.matchDate = matchDate
        this.matchTime = matchTime
    }

    companion object {
        const val ID = "ID_"

        // favorite matches
        const val TABLE_FAVORITE = "TABLE_FAVORITE_MATCHES"

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
        
        // list of details tables
        val DETAILS_TABLES = listOf(
            // home
            TABLE_HOME_GOALS,
            TABLE_HOME_RED_CARDS,
            TABLE_HOME_YELLOW_CARDS,
            TABLE_HOME_GOALKEEPER,
            TABLE_HOME_DEFENSE,
            TABLE_HOME_MIDFIELD,
            TABLE_HOME_FORWARD,
            TABLE_HOME_SUBSTITUTES,

            // away
            TABLE_AWAY_GOALS,
            TABLE_AWAY_RED_CARDS,
            TABLE_AWAY_YELLOW_CARDS,
            TABLE_AWAY_GOALKEEPER,
            TABLE_AWAY_DEFENSE,
            TABLE_AWAY_MIDFIELD,
            TABLE_AWAY_FORWARD,
            TABLE_AWAY_SUBSTITUTES)
    }
}