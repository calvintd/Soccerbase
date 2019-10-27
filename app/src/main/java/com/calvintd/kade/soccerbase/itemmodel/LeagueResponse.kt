package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class LeagueResponse (
    @SerializedName("countrys")
    var leagues: List<Leagues>)