package com.calvintd.kade.soccerbase.view

import android.database.sqlite.SQLiteConstraintException

interface ItemDetailsView {
    fun checkedFavorite(isFavorited: Boolean)
    fun addedToFavorites()
    fun removedFromFavorites()
    fun showError(message: String?)
}