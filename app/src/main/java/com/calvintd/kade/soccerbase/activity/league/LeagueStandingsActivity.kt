package com.calvintd.kade.soccerbase.activity.league

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.Standings
import com.calvintd.kade.soccerbase.itemmodel.StandingsResponse
import com.calvintd.kade.soccerbase.presenter.LeagueStandingsPresenter
import com.calvintd.kade.soccerbase.repository.StandingsResponseRepository
import com.calvintd.kade.soccerbase.view.LeagueStandingsView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import retrofit2.Response

class LeagueStandingsActivity : AppCompatActivity(), LeagueStandingsView {
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.league_standings_activity_title)

        val presenter = LeagueStandingsPresenter(this, StandingsResponseRepository())
        val league = intent.getParcelableExtra("league") as League
        val leagueId = league.leagueId!!
        val leagueName = league.name!!

        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            progressBar = progressBar {
                id = R.id.pbLeagueStandingsProgressBar
                padding = 128
            }.lparams(width = matchParent, height = matchParent)

            textView = textView {
                id = R.id.tvLeagueStandingsResults
                padding = 32
                textSize = 16f
                visibility = View.GONE
                gravity = Gravity.CENTER
            }.lparams(width = matchParent, height = wrapContent)

            scrollView {
                // insert table here

                constraintLayout {
                    lparams(width = matchParent, height = matchParent)

                    applyConstraintSet {
                        val parent = ConstraintSet.PARENT_ID
                        val start = ConstraintSetBuilder.Side.START
                        val end = ConstraintSetBuilder.Side.END
                        val top = ConstraintSetBuilder.Side.TOP
                        val bottom = ConstraintSetBuilder.Side.BOTTOM
                        val margin = 16

                        
                    }
                }
            }
        }

        presenter.loadStandings(leagueId, leagueName)
    }

    override fun loadStandings(standings: List<Standings>, leagueName: String) {
        runOnUiThread {
            changeUI(true, leagueName)
        }
    }

    override fun showNoStandingsFound(leagueName: String) {
        runOnUiThread {
            changeUI(false, leagueName)
        }
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        runOnUiThread {
            toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
        }
    }

    override fun onStandingsDataLoaded(data: StandingsResponse?) {
        Log.i(resources.getString((R.string.logging_loaded_log_title)), resources.getString(R.string.logging_loaded_log_message))
    }

    override fun onStandingsDataError(response: Response<StandingsResponse>) {
        showResponseError(response.code(), response.errorBody())
    }

    private fun changeUI(hasStandings: Boolean, leagueName: String) {
        progressBar.visibility = View.GONE
        textView.visibility = View.VISIBLE
        if (hasStandings) {
            textView.text = String.format(resources.getString(R.string.league_standings_display), leagueName)
        } else {
            textView.text = String.format(resources.getString(R.string.league_standings_not_found), leagueName)
        }
    }
}