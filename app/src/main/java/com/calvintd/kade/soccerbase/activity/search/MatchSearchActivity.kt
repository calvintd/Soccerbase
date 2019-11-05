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
import com.calvintd.kade.soccerbase.activity.details.ItemDetailsActivity
import com.calvintd.kade.soccerbase.adapter.MatchAdapter
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.presenter.MatchSearchPresenter
import com.calvintd.kade.soccerbase.repository.MatchResponseRepository
import com.calvintd.kade.soccerbase.utils.test.EspressoIdlingResource
import com.calvintd.kade.soccerbase.view.MatchSearchView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import retrofit2.Response

class MatchSearchActivity : AppCompatActivity(), MatchSearchView {
    private lateinit var searchView: SearchView
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.match_search_activity_title)

        val presenter = MatchSearchPresenter(this, MatchResponseRepository())

        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            searchView = searchView {
                id = R.id.svMatchSearchView
                isSubmitButtonEnabled = true
                isIconified = false
                queryHint = resources.getString(R.string.search_query_hint)

                onQueryTextListener {
                    onQueryTextSubmit {
                        progressBar.visibility = View.VISIBLE
                        textView.visibility = View.GONE
                        val query = searchView.query.toString()
                        EspressoIdlingResource.increment()
                        presenter.loadMatchesByQuery(query)
                        false
                    }
                }
            }.lparams(width = matchParent, height = wrapContent)

            textView = textView {
                id = R.id.tvMatchSearchResults
                padding = 32
                textSize = 16f
                visibility = View.GONE
                gravity = Gravity.CENTER
            }.lparams(width = matchParent, height = wrapContent)

            progressBar = progressBar {
                id = R.id.pbMatchSearchProgressBar
                padding = 128
                visibility = View.GONE
            }.lparams(width = matchParent, height = matchParent)

            scrollView {
                recyclerView = recyclerView {
                    id = R.id.rvMatchSearchRecyclerView
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@MatchSearchActivity)
                    visibility = View.GONE
                }
            }
        }
    }

    override fun loadMatchesByQuery(matches: List<Match>, query: String) {
        runOnUiThread {
            if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                EspressoIdlingResource.decrement()
            }
            textView.text = String.format(
                resources.getString(R.string.search_results_for_query),
                query
            )
            recyclerView.adapter = MatchAdapter(matches) {
                startActivity<ItemDetailsActivity>(
                    "match" to it
                )
            }
            recyclerView.adapter!!.notifyDataSetChanged()
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }
    }

    override fun showNoResultsFound(query: String) {
        runOnUiThread {
            if (!EspressoIdlingResource.idlingresource.isIdleNow) {
                EspressoIdlingResource.decrement()
            }
            textView.text = String.format(
                resources.getString(R.string.search_no_results_found),
                query
            )
            recyclerView.adapter = MatchAdapter(listOf()) {

            }
            recyclerView.adapter?.notifyDataSetChanged()
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        runOnUiThread {
            toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
        }
    }

    override fun onMatchDataLoaded(data: MatchResponse?) {
        Log.i(resources.getString((R.string.logging_loaded_log_title)), resources.getString(R.string.logging_loaded_log_message))
    }

    override fun onMatchDataError(response: Response<MatchResponse>) {
        showResponseError(response.code(), response.errorBody())
    }
}