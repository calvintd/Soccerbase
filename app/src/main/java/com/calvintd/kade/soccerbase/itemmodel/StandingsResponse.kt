package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class StandingsResponse (
    @SerializedName("table")
    var teams: List<StandingsResponseItem>)