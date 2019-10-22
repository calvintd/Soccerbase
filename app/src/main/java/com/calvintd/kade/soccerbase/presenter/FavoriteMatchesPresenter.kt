package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.view.FavoriteMatchesView

class FavoriteMatchesPresenter(private val view: FavoriteMatchesView) {
    val matches = ArrayList<Match>()

    fun loadFavorites() {

    }
}