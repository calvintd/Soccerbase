package com.calvintd.kade.thesportdb.model

import com.google.gson.annotations.SerializedName

class LeagueResponse (
    @SerializedName("countrys")
    var leagues: List<Leagues>) {

    class Leagues (
        @SerializedName("idLeague")
        var leagueId: Int?,
        @SerializedName("strLeague")
        var name: String?,
        @SerializedName("strBadge")
        var badge: String?,
        @SerializedName("strDescriptionEN")
        var description: String?
    )
}