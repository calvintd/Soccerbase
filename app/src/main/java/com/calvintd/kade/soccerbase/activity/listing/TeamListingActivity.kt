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
    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var leagues: List<League>
    private lateinit var teams: List<Team>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.team_listing_activity_title)

        val presenter =  TeamListingPresenter(this, LeagueResponseRepository(), TeamResponseRepository())

        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            spinner = spinner {
                id = R.id.spTeamListingSpinner
            }.lparams(width = matchParent, height = wrapContent)

            button = button {
                id = R.id.btnTeamListingButton
                textSize = 20f
                text = resources.getString(R.string.team_listing_find_teams_button)
                visibility = View.GONE

                onClick {
                    button.visibility = View.GONE
                }
            }.lparams(width = matchParent, height = wrapContent)

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
                visibility = View.GONE
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


    }
    override fun loadDataIntoSpinner(leagues: List<League>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        runOnUiThread {
            toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
        }
    }

    override fun onLeagueDataLoaded(data: LeagueResponse?) {
        Log.i(resources.getString((R.string.logging_loaded_log_title)), resources.getString(R.string.logging_loaded_log_message))
    }

    override fun onLeagueDataError(response: Response<LeagueResponse>) {
        showResponseError(response.code(), response.errorBody())
    }

    override fun onTeamDataLoaded(data: TeamResponse?) {
        Log.i(resources.getString((R.string.logging_loaded_log_title)), resources.getString(R.string.logging_loaded_log_message))
    }

    override fun onTeamDataError(response: Response<TeamResponse>) {
        showResponseError(response.code(), response.errorBody())
    }
}