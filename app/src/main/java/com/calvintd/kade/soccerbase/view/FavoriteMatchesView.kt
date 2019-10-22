package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.model.MatchAdapterModel

interface FavoriteMatchesView {
    fun loadFavoriteMatches(model: MatchAdapterModel)
    fun showNoFavoriteMatches()
}