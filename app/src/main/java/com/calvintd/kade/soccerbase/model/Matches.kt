package com.calvintd.kade.soccerbase.model

import com.google.gson.annotations.SerializedName

class Matches (
    // general data
    @SerializedName("idEvent")
    var matchId: Int?,
    @SerializedName("strSport")
    var sport: String?,
    @SerializedName("idHomeTeam")
    var homeTeamId: String?,
    @SerializedName("idAwayTeam")
    var awayTeamId: String?,
    @SerializedName("strHomeTeam")
    var homeName: String?,
    @SerializedName("strAwayTeam")
    var awayName: String?,
    @SerializedName("intHomeScore")
    var homeSoore: Int?,
    @SerializedName("intAwayScore")
    var awayScore: Int?,
    @SerializedName("dateEvent")
    var matchDate: String?,
    @SerializedName("strTime")
    var matchTime: String?,

    // home
    @SerializedName("strHomeGoalDetails")
    var homeGoals: String?,
    @SerializedName("strHomeRedCards")
    var homeRedCards: String?,
    @SerializedName("strHomeYellowCards")
    var homeYellowCards: String?,
    @SerializedName("strHomeLineupGoalkeeper")
    var homeGoalkeeper: String?,
    @SerializedName("strHomeLineupDefense")
    var homeDefense: String?,
    @SerializedName("strHomeLineupMidfield")
    var homeMidfield: String?,
    @SerializedName("strHomeLineupForward")
    var homeForward: String?,
    @SerializedName("strHomeLineupSubstitutes")
    var homeSubstitutes: String?,

    // away
    @SerializedName("strAwayGoalDetails")
    var awayGoals: String?,
    @SerializedName("strAwayRedCards")
    var awayRedCards: String?,
    @SerializedName("strAwayYellowCards")
    var awayYellowCards: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    var awayGoalkeeper: String?,
    @SerializedName("strAwayLineupDefense")
    var awayDefense: String?,
    @SerializedName("strAwayLineupMidfield")
    var awayMidfield: String?,
    @SerializedName("strAwayLineupForward")
    var awayForward: String?,
    @SerializedName("strAwayLineupSubstitutes")
    var awaySubstitutes: String?
)