package com.calvintd.kade.soccerbase.presenter

import android.database.sqlite.SQLiteConstraintException
import com.calvintd.kade.soccerbase.database.DatabaseHelper
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.utils.database.MatchDatabaseDetailsOrganizer
import com.calvintd.kade.soccerbase.view.FavoriteMatchesView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavoriteMatchesPresenter(private val view: FavoriteMatchesView) {
    val matches = mutableListOf<Match>()

    fun loadFavorites(helper: DatabaseHelper) {
        matches.clear()

        val organizer = MatchDatabaseDetailsOrganizer(helper)

        try {
            helper.use {
                select(Match.TABLE_FAVORITE)
                    .exec {
                        if (count == 0) {
                            view.showNoFavoriteMatches()
                        } else {
                            val parsedMatchesList = parseList(classParser<Match>())
                            matches.addAll(parsedMatchesList)
                        }
                    }
                for (i in matches.indices) {
                    matches[i] = organizer.selectDetails(matches[i])
                }

                view.loadFavoriteMatches(matches)
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e.localizedMessage)
        }
    }
}