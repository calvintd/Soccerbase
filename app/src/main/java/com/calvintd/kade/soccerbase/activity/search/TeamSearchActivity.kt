package com.calvintd.kade.soccerbase.activity.search

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.activity.details.TeamDetailsActivity
import com.calvintd.kade.soccerbase.activity.listing.PlayerListingActivity
import com.calvintd.kade.soccerbase.adapter.TeamAdapter
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.presenter.TeamSearchPresenter
import com.calvintd.kade.soccerbase.repository.TeamResponseRepository
import com.calvintd.kade.soccerbase.utils.test.EspressoIdlingResource
import com.calvintd.kade.soccerbase.view.TeamSearchView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import retrofit2.Response

class TeamSearchActivity : AppCompatActivity(), TeamSearchView {

    private lateinit var searchView: SearchView
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.team_search_activity_title)

        val presenter = TeamSearchPresenter(this, TeamResponseRepository())

        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            searchView = searchView {
                id = R.id.svTeamSearchView
                isSubmitButtonEnabled = true
                isIconified = false
                queryHint = resources.getString(R.string.search_query_hint)

                onQueryTextListener {
                    onQueryTextSubmit {
                        progressBar.visibility = View.VISIBLE
                        textView.visibility = View.GONE
                        val query = searchView.query.toString()
                        EspressoIdlingResource.increment()
                        presenter.loadTeamsByQuery(query)
                        false
                    }
                }
            }.lparams(width = matchParent, height = wrapContent)

            textView = textView {
                id = R.id.tvTeamSearchResults
                padding = 32
                textSize = 16f
                visibility = View.GONE
                gravity = Gravity.CENTER
            }.lparams(width = matchParent, height = wrapContent)

            progressBar = progressBar {
                id = R.id.pbTeamSearchProgressBar
                padding = 128
                visibility = View.GONE
            }.lparams(width = matchParent, height = matchParent)

            scrollView {
                recyclerView = recyclerView {
                    id = R.id.rvTeamSearchRecyclerView
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@TeamSearchActivity)
                    visibility = View.GONE
                }
            }
        }
    }

    override fun loadTeamsByQuery(teams: List<Team>, query: String) {
        runOnUiThread {
            if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                EspressoIdlingResource.decrement()
            }
            textView.text = String.format(
                resources.getString(R.string.search_results_for_query),
                query
            )
            recyclerView.adapter = TeamAdapter(teams, {
                startActivity<TeamDetailsActivity>(
                    "team" to it
                )
            }, {
                startActivity<PlayerListingActivity>(
                    "team" to it
                )
            })
            progressBar.visibility = View.GONE
            recyclerView.adapter!!.notifyDataSetChanged()
            textView.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun showNoResultsFound(query: String) {
        runOnUiThread {
            if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                EspressoIdlingResource.decrement()
            }
            textView.visibility = View.VISIBLE
            textView.text = String.format(
                resources.getString(R.string.search_no_results_found),
                query
            )
            progressBar.visibility = View.GONE
            recyclerView.adapter = TeamAdapter(listOf(), {

            }, {

            })
            recyclerView.adapter?.notifyDataSetChanged()
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