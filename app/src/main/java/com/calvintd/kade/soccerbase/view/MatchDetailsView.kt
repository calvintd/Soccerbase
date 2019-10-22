package com.calvintd.kade.soccerbase.view

import android.database.sqlite.SQLiteConstraintException

interface MatchDetailsView {
    fun checkedFavorite(isFavorited: Boolean)
    fun addedToFavorites()
    fun removedFromFavorites()
    fun showError(e: SQLiteConstraintException)
}