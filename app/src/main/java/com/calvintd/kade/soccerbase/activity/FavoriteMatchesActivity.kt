package com.calvintd.kade.soccerbase.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.database.database
import com.calvintd.kade.soccerbase.model.MatchAdapterModel
import com.calvintd.kade.soccerbase.presenter.FavoriteMatchesPresenter
import com.calvintd.kade.soccerbase.view.FavoriteMatchesView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FavoriteMatchesActivity : AppCompatActivity(), FavoriteMatchesView {
    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.favorite_matches_activity_title)

        val presenter = FavoriteMatchesPresenter(this)

        scrollView {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)

                textView = textView {
                    padding = 32
                    textSize = 16f
                    visibility = View.GONE
                    gravity = Gravity.CENTER
                    text = resources.getString(R.string.favorite_matches_no_favorites_yet)
                }.lparams(width = matchParent, height = wrapContent)

                recyclerView = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@FavoriteMatchesActivity)
                    visibility = View.GONE
                }
            }
        }

        presenter.loadFavorites(database)
    }

    override fun loadFavoriteMatches(model: MatchAdapterModel) {
        recyclerView.visibility = View.VISIBLE
        recyclerView.adapter = model.getMatchAdapter()
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun showNoFavoriteMatches() {
        textView.visibility = View.VISIBLE
    }

    override fun showError(e: SQLiteConstraintException) {
        toast(e.localizedMessage)
    }
}