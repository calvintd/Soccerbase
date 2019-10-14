package com.calvintd.kade.soccerbase.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.presenter.LeagueListingPresenter
import com.calvintd.kade.soccerbase.view.LeagueListingView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import retrofit2.HttpException

class LeagueListingActivity : AppCompatActivity(), LeagueListingView {
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getText(R.string.league_listing_activity_title)

        val presenter = LeagueListingPresenter(this)

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

        presenter.loadData(recyclerView)
    }

    override fun loadData() {
        recyclerView?.adapter?.notifyDataSetChanged()
        progressBar?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE

    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
    }

    override fun showException(e: HttpException) {
        toast(String.format(resources.getString(R.string.error_messages_http_exception), e.message()))
    }
}