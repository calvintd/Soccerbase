package com.calvintd.kade.soccerbase.activity.favorite

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.activity.details.ItemDetailsActivity
import com.calvintd.kade.soccerbase.adapter.MatchAdapter
import com.calvintd.kade.soccerbase.database.database
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.presenter.FavoriteMatchesPresenter
import com.calvintd.kade.soccerbase.view.FavoriteMatchesView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FavoriteMatchesActivity : AppCompatActivity(), FavoriteMatchesView {
    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: FavoriteMatchesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.favorite_matches_activity_title)

        presenter = FavoriteMatchesPresenter(this)

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

    override fun loadFavoriteMatches(matches: List<Match>) {
        recyclerView.adapter = MatchAdapter(matches) {
            startActivity<ItemDetailsActivity>(
                "match" to it
            )
        }
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.visibility = View.VISIBLE
    }

    override fun showNoFavoriteMatches() {
        textView.visibility = View.VISIBLE
    }

    override fun showError(message: String?) {
        toast(String.format(resources.getString(R.string.error_messages_sqlite_exception), message))
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFavorites(database)
    }
}