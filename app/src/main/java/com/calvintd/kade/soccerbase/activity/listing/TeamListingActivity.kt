package com.calvintd.kade.soccerbase.activity.listing

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.activity.details.TeamDetailsActivity
import com.calvintd.kade.soccerbase.adapter.TeamAdapter
import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.presenter.TeamListingPresenter
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepository
import com.calvintd.kade.soccerbase.repository.TeamResponseRepository
import com.calvintd.kade.soccerbase.view.TeamListingView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import retrofit2.Response

class TeamListingActivity : AppCompatActivity(), TeamListingView {
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.team_listing_activity_title)

        val presenter =  TeamListingPresenter(this, TeamResponseRepository())
        val league = intent.getParcelableExtra("league") as League
        val leagueId = league.leagueId!!
        val leagueName = league.name!!

        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            textView = textView {
                id = R.id.tvTeamListingResults
                padding = 32
                textSize = 16f
                visibility = View.GONE
                gravity = Gravity.CENTER
            }.lparams(width = matchParent, height = wrapContent)

            progressBar = progressBar {
                id = R.id.pbTeamListingProgressBar
                padding = 128
            }.lparams(width = matchParent, height = matchParent)

            scrollView {
                recyclerView = recyclerView {
                    id = R.id.rvTeamListingRecyclerView
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@TeamListingActivity)
                    visibility = View.GONE
                }
            }
        }

        presenter.loadLeagueTeams(leagueId, leagueName)
    }

    override fun loadLeagueTeams(teams: List<Team>, leagueName: String) {
        runOnUiThread {
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
            textView.text = String.format(resources.getString(R.string.team_listing_load_league_teams), leagueName)
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    override fun showNoTeamsFound(leagueName: String) {
        runOnUiThread {
            recyclerView.adapter = TeamAdapter(listOf(), {}, {})
            recyclerView.adapter?.notifyDataSetChanged()
            textView.text = String.format(resources.getString(R.string.team_listing_show_no_teams_found), leagueName)
            textView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        runOnUiThread {
            toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
        }
    }

    override fun onTeamDataLoaded(data: TeamResponse?) {
        Log.i(resources.getString((R.string.logging_loaded_log_title)), resources.getString(R.string.logging_loaded_log_message))
    }

    override fun onTeamDataError(response: Response<TeamResponse>) {
        showResponseError(response.code(), response.errorBody())
    }
}