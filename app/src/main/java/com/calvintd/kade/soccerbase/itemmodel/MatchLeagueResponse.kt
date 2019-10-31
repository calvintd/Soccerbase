package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

// digunakan untuk mengambil data jadwal pertandingan
class MatchLeagueResponse (
    @SerializedName("events")
    val matches: List<MatchResponseItem>)