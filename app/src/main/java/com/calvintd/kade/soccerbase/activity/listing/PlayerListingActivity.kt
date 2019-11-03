package com.calvintd.kade.soccerbase.activity.listing

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.activity.details.PlayerDetailsActivity
import com.calvintd.kade.soccerbase.adapter.PlayerAdapter
import com.calvintd.kade.soccerbase.itemmodel.Player
import com.calvintd.kade.soccerbase.itemmodel.PlayerResponse
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.presenter.PlayerListingPresenter
import com.calvintd.kade.soccerbase.repository.PlayerResponseRepository
import com.calvintd.kade.soccerbase.view.PlayerListingView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import retrofit2.Response

class PlayerListingActivity : AppCompatActivity(), PlayerListingView {
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.player_listing_activity_title)

        val presenter = PlayerListingPresenter(this, PlayerResponseRepository())
        val team = intent.getParcelableExtra("team") as Team
        val teamId = team.teamId!!
        val teamName = team.teamName!!

        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            textView = textView {
                id = R.id.tvPlayerListingResults
                padding = 32
                textSize = 16f
                visibility = View.GONE
                gravity = Gravity.CENTER
            }.lparams(width = matchParent, height = wrapContent)

            progressBar = progressBar {
                id = R.id.pbPlayerListingProgressBar
                padding = 128
            }.lparams(width = matchParent, height = matchParent)

            scrollView {
                recyclerView = recyclerView {
                    id = R.id.rvPlayerListingRecyclerView
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@PlayerListingActivity)
                    visibility = View.GONE
                }
            }
        }

        presenter.loadTeamPlayers(teamId, teamName)
    }

    override fun loadTeamPlayers(players: List<Player>, teamName: String) {
        runOnUiThread {
            recyclerView.adapter = PlayerAdapter(players) {
                startActivity<PlayerDetailsActivity>(
                    "player" to it
                )
            }
            recyclerView.adapter!!.notifyDataSetChanged()
            recyclerView.visibility = View.VISIBLE
            textView.text = String.format(resources.getString(R.string.player_listing_load_team_players), teamName)
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    override fun showNoPlayersFound(teamName: String) {
        runOnUiThread {
            recyclerView.adapter = PlayerAdapter(listOf()) {}
            recyclerView.adapter?.notifyDataSetChanged()
            textView.text = String.format(
                resources.getString(R.string.players_listing_show_no_players_found),
                teamName
            )
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        runOnUiThread {
            toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
        }
    }

    override fun onPlayerDataLoaded(data: PlayerResponse?) {
        Log.i(resources.getString((R.string.logging_loaded_log_title)), resources.getString(R.string.logging_loaded_log_message))
    }

    override fun onPlayerDataError(response: Response<PlayerResponse>) {
        showResponseError(response.code(), response.errorBody())
    }
}