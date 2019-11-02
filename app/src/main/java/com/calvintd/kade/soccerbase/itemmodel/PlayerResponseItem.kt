package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class PlayerResponseItem (
    @SerializedName("idPlayer")
    var playerId: Int?,
    @SerializedName("strNationality")
    var playerNationality: String?,
    @SerializedName("strPlayer")
    var playerName: String?,
    @SerializedName("strTeam")
    var playerTeam: String?,
    @SerializedName("dateBorn")
    var playerBirthDate: String?,
    @SerializedName("strBirthLocation")
    var playerBirthLocation: String?,
    @SerializedName("dateSigned")
    var playerSignedDate: String?,
    @SerializedName("strWage")
    var playerWage: String?,
    @SerializedName("strDescriptionEN")
    var playerDescription: String?,
    @SerializedName("strPosition")
    var playerPosition: String?,
    @SerializedName("strFanart1")
    var playerFanart: String?
)