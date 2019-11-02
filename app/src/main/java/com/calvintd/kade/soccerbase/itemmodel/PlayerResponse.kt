package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class PlayerResponse (
    @SerializedName("player")
    var players: List<PlayerResponseItem>)