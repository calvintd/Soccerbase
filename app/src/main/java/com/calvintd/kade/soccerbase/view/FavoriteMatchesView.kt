package com.calvintd.kade.soccerbase.view

import com.calvintd.kade.soccerbase.itemmodel.Match

interface FavoriteMatchesView {
    fun loadFavoriteMatches(matches: List<Match>)
    fun showNoFavoriteMatches()
    fun showError(message: String?)
}