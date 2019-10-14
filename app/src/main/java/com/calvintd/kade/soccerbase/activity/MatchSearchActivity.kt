package com.calvintd.kade.soccerbase.activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.presenter.MatchSearchPresenter
import com.calvintd.kade.soccerbase.view.MatchSearchView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.sdk27.coroutines.onSearchClick
import retrofit2.HttpException

class MatchSearchActivity : AppCompatActivity(), MatchSearchView {
    private var searchView: SearchView? = null
    private var textView: TextView? = null
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.match_search_activity_title)

        val presenter = MatchSearchPresenter(this)

        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            searchView = searchView {
                isSubmitButtonEnabled = true
                isIconified = false
                queryHint = resources.getString(R.string.match_search_query_hint)

                onQueryTextListener {
                    onQueryTextSubmit {
                        progressBar?.visibility = View.VISIBLE
                        val query = searchView?.query.toString()
                        presenter.loadMatchByQuery(recyclerView, query)
                        false
                    }
                }
            }.lparams(width = matchParent, height = wrapContent)

            textView = textView {
                padding = 32
                textSize = 16f
                visibility = View.GONE
                gravity = Gravity.CENTER
            }.lparams(width = matchParent, height = wrapContent)

            progressBar = progressBar {
                padding = 128
                visibility = View.GONE
            }.lparams(width = matchParent, height = matchParent)

            scrollView {
                recyclerView = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@MatchSearchActivity)
                    visibility = View.GONE
                }
            }
        }
    }

    override fun loadMatchByQuery(query: String) {
        textView?.visibility = View.VISIBLE
        textView?.text = String.format(
            resources.getString(R.string.match_search_search_results_for_query),
            query)
        recyclerView?.adapter?.notifyDataSetChanged()
        progressBar?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE
    }

    override fun showNoResultsFound(query: String) {
        textView?.visibility = View.VISIBLE
        textView?.text = String.format(
            resources.getString(R.string.match_search_no_search_results_found),
            query)
        progressBar?.visibility = View.GONE
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        toast("Error in fetching response through API: $code $responseBody")
    }

    override fun showException(e: HttpException) {
        toast("The following exception happened: ${e.message()}")
    }
}