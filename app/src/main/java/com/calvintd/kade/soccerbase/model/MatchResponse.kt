package com.calvintd.kade.soccerbase.model

import com.google.gson.annotations.SerializedName

class MatchResponse (
    @SerializedName("event")
    val matches: List<Matches>)