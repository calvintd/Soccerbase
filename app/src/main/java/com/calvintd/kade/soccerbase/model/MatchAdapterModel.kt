package com.calvintd.kade.soccerbase.model

import com.calvintd.kade.soccerbase.adapter.MatchAdapter

class MatchAdapterModel (adapter: MatchAdapter) {
    private val adapter = adapter

    fun getMatchAdapter() = adapter
}