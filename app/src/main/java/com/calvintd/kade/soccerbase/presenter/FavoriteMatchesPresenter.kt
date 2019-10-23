package com.calvintd.kade.soccerbase.presenter

import android.database.sqlite.SQLiteConstraintException
import com.calvintd.kade.soccerbase.adapter.MatchAdapter
import com.calvintd.kade.soccerbase.database.DatabaseHelper
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.model.MatchAdapterModel
import com.calvintd.kade.soccerbase.utils.MatchDatabaseDetailsOrganizer
import com.calvintd.kade.soccerbase.view.FavoriteMatchesView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavoriteMatchesPresenter(private val view: FavoriteMatchesView) {
    val matches = ArrayList<Match>()

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

                val matchAdapter = MatchAdapter(matches)
                val matchAdapterModel = MatchAdapterModel(matchAdapter)
                view.loadFavoriteMatches(matchAdapterModel)
            }
        } catch (e: SQLiteConstraintException) {
            view.showError(e)
        }
    }
}