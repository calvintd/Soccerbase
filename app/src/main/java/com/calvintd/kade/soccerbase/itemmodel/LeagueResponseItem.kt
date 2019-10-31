package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class LeagueResponseItem (
    @SerializedName("idLeague")
    var leagueId: Int?,
    @SerializedName("strLeague")
    var name: String?,
    @SerializedName("strBadge")
    var badge: String?,
    @SerializedName("strDescriptionEN")
    var description: String?
)