package com.calvintd.kade.soccerbase.activity.league

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.adapter.StandingsAdapter
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
import org.jetbrains.anko.recyclerview.v7.recyclerView
import retrofit2.Response

class LeagueStandingsActivity : AppCompatActivity(), LeagueStandingsView {
    private lateinit var progressBar: ProgressBar
    private lateinit var header: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var footer: TextView

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

            constraintLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = 16

                header = textView {
                    id = R.id.tvLeagueStandingsHeader
                    padding = 32
                    textSize = 16f
                    visibility = View.GONE
                    gravity = Gravity.CENTER
                }.lparams(width = matchConstraint, height = wrapContent)

                val scrollView = scrollView {
                    id = R.id.scvLeagueStandingsScrollView
                    recyclerView = recyclerView {
                        id = R.id.rvLeagueStandingsTable
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(this@LeagueStandingsActivity)
                        visibility = View.GONE
                    }
                }.lparams(width = matchConstraint, height = wrapContent)

                footer = textView {
                    id = R.id.tvLeagueStandingsFooter
                    text = resources.getString(R.string.league_standings_note)
                    padding = 16
                    textSize = 12f
                    visibility = View.GONE
                    gravity = Gravity.START
                }.lparams(width = matchConstraint, height = wrapContent)

                applyConstraintSet {
                    val parent = ConstraintSet.PARENT_ID
                    val start = ConstraintSetBuilder.Side.START
                    val end = ConstraintSetBuilder.Side.END
                    val top = ConstraintSetBuilder.Side.TOP
                    val bottom = ConstraintSetBuilder.Side.BOTTOM

                    header {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to top of parent
                        )
                    }

                    scrollView {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of header,
                            bottom to top of footer
                        )
                    }

                    footer {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            bottom to bottom of parent
                        )
                    }
                }
            }
        }

        presenter.loadStandings(leagueId, leagueName)
    }

    override fun loadStandings(standings: List<Standings>, leagueName: String) {
        runOnUiThread {
            recyclerView.adapter = StandingsAdapter(standings)
            recyclerView.adapter!!.notifyDataSetChanged()
            changeUI(true, leagueName)
        }
    }

    override fun showNoStandingsFound(leagueName: String) {
        runOnUiThread {
            recyclerView.adapter = StandingsAdapter(listOf())
            recyclerView.adapter?.notifyDataSetChanged()
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
        header.visibility = View.VISIBLE
        if (hasStandings) {
            header.text = String.format(resources.getString(R.string.league_standings_display), leagueName)
            recyclerView.visibility = View.VISIBLE
            footer.visibility = View.VISIBLE
        } else {
            header.text = String.format(resources.getString(R.string.league_standings_not_found), leagueName)
        }
    }
}