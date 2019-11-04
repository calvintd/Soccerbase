package com.calvintd.kade.soccerbase.activity.favorite

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.activity.details.TeamDetailsActivity
import com.calvintd.kade.soccerbase.activity.listing.PlayerListingActivity
import com.calvintd.kade.soccerbase.adapter.TeamAdapter
import com.calvintd.kade.soccerbase.database.database
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.presenter.FavoriteTeamsPresenter
import com.calvintd.kade.soccerbase.view.FavoriteTeamsView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FavoriteTeamsActivity : AppCompatActivity(), FavoriteTeamsView {
    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: FavoriteTeamsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.favorite_teams_activity_title)

        presenter = FavoriteTeamsPresenter(this)

        scrollView {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)

                textView = textView {
                    padding = 32
                    textSize = 16f
                    visibility = View.GONE
                    gravity = Gravity.CENTER
                    text = resources.getString(R.string.favorite_teams_no_favorites_yet)
                }.lparams(width = matchParent, height = wrapContent)

                recyclerView = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@FavoriteTeamsActivity)
                    visibility = View.GONE
                }
            }
        }
    }

    override fun loadFavoriteTeams(teams: List<Team>) {
        recyclerView.adapter = TeamAdapter(teams, {
            startActivity<TeamDetailsActivity>(
                "team" to it
            )
        }, {
            startActivity<PlayerListingActivity>(
                "team" to it
            )
        })
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.visibility = View.VISIBLE
    }

    override fun showNoFavoriteTeams() {
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