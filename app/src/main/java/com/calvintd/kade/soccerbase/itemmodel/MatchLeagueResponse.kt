package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class MatchLeagueResponse (
    @SerializedName("events")
    val matches: List<Matches>)