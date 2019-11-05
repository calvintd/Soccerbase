package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class StandingsResponseItem (
    @SerializedName("name")
    var name: String?,
    @SerializedName("played")
    var matches: Int?,
    @SerializedName("goalsfor")
    var goalsFor: Int?,
    @SerializedName("goalsagainst")
    var goalsAgainst: Int?,
    @SerializedName("goalsdifference")
    var goalsDifference: Int?,
    @SerializedName("win")
    var wins: Int?,
    @SerializedName("draw")
    var draws: Int?,
    @SerializedName("loss")
    var losses: Int?,
    @SerializedName("total")
    var points: Int?
)