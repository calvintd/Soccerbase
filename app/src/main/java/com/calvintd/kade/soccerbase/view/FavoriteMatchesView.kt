package com.calvintd.kade.soccerbase.view

import android.database.sqlite.SQLiteConstraintException
import com.calvintd.kade.soccerbase.model.MatchAdapterModel

interface FavoriteMatchesView {
    fun loadFavoriteMatches(model: MatchAdapterModel)
    fun showNoFavoriteMatches()
    fun showError(e: SQLiteConstraintException)
}