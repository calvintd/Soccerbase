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
import com.calvintd.kade.soccerbase.model.MatchAdapterModel
import com.calvintd.kade.soccerbase.presenter.MatchSearchPresenter
import com.calvintd.kade.soccerbase.view.MatchSearchView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import retrofit2.HttpException

class MatchSearchActivity : AppCompatActivity(), MatchSearchView {
    private lateinit var searchView: SearchView
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

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
                        progressBar.visibility = View.VISIBLE
                        textView.visibility = View.GONE
                        val query = searchView.query.toString()
                        presenter.loadMatchesByQuery(query)
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

    override fun loadMatchesByQuery(model: MatchAdapterModel, query: String) {
        textView.visibility = View.VISIBLE
        textView.text = String.format(
            resources.getString(R.string.match_search_search_results_for_query),
            query)
        recyclerView.adapter = model.getMatchAdapter()
        recyclerView.adapter!!.notifyDataSetChanged()
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showNoResultsFound(query: String) {
        textView.visibility = View.VISIBLE
        textView.text = String.format(
            resources.getString(R.string.match_search_no_search_results_found),
            query)
        recyclerView.adapter?.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
    }

    override fun showException(e: HttpException) {
        toast(String.format(resources.getString(R.string.error_messages_http_exception), e.message()))
    }
}