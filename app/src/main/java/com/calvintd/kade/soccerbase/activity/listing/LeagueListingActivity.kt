package com.calvintd.kade.soccerbase.activity.listing

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.activity.league.LeagueDescriptionActivity
import com.calvintd.kade.soccerbase.activity.league.LeagueScheduleActivity
import com.calvintd.kade.soccerbase.activity.league.LeagueStandingsActivity
import com.calvintd.kade.soccerbase.adapter.LeagueAdapter
import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.presenter.LeagueListingPresenter
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepository
import com.calvintd.kade.soccerbase.view.LeagueListingView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import retrofit2.Response

class LeagueListingActivity : AppCompatActivity(), LeagueListingView {
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getText(R.string.league_listing_activity_title)

        val presenter = LeagueListingPresenter(this, LeagueResponseRepository())

        scrollView{
            verticalLayout{
                lparams(width = matchParent, height = matchParent)

                progressBar = progressBar {
                    padding = 128
                }.lparams(width = matchParent, height = matchParent)

                recyclerView = recyclerView{
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@LeagueListingActivity)
                    visibility = View.GONE
                }
            }
        }

        presenter.loadData()
    }

    override fun loadData(leagues: List<League>) {
        runOnUiThread {
            recyclerView.adapter = LeagueAdapter(leagues, {
                startActivity<LeagueStandingsActivity>(
                    "league" to it
                )
            }, {
                startActivity<LeagueDescriptionActivity>(
                    "league" to it
                )
            }, {
                startActivity<LeagueScheduleActivity>(
                    "league" to it
                )
            })
            recyclerView.adapter!!.notifyDataSetChanged()
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        runOnUiThread {
            toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
        }
    }

    override fun onDataLoaded(data: LeagueResponse?) {
        Log.i(resources.getString((R.string.logging_loaded_log_title)), resources.getString(R.string.logging_loaded_log_message))
    }

    override fun onDataError(response: Response<LeagueResponse>) {
        showResponseError(response.code(), response.errorBody())
    }
}