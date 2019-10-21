package com.calvintd.kade.soccerbase.model

import com.calvintd.kade.soccerbase.adapter.LeagueAdapter

class LeagueAdapterModel (adapter: LeagueAdapter) {
    private val adapter = adapter

    fun getLeagueAdapter() = adapter
}