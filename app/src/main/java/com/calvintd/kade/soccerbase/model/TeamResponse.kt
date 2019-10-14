package com.calvintd.kade.soccerbase.model

import com.google.gson.annotations.SerializedName

class TeamResponse (
    @SerializedName("teams")
    var teams: List<Teams>) {

    class Teams (
        @SerializedName("idTeam")
        var teamId: Int?,
        @SerializedName("strTeamBadge")
        var teamBadge: String?
    )
}