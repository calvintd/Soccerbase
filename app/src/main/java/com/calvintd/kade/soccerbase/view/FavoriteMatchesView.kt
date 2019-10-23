package com.calvintd.kade.soccerbase.view

import android.database.sqlite.SQLiteConstraintException
import com.calvintd.kade.soccerbase.itemmodel.Match

interface FavoriteMatchesView {
    fun loadFavoriteMatches(matches: ArrayList<Match>)
    fun showNoFavoriteMatches()
    fun showError(e: SQLiteConstraintException)
}