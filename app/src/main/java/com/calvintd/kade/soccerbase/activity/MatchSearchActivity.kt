package com.calvintd.kade.soccerbase.activity

import android.os.Bundle
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
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onQueryTextListener
import org.jetbrains.anko.sdk27.coroutines.onSearchClick

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
    }
}