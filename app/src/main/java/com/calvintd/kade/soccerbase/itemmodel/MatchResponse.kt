package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

// digunakan dalam pencarian pertandingan
class MatchResponse (
    @SerializedName("event")
    val matches: List<MatchResponseItem>)