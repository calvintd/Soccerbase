package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class TeamResponse (
    @SerializedName("teams")
    var teams: List<TeamResponseItem>)