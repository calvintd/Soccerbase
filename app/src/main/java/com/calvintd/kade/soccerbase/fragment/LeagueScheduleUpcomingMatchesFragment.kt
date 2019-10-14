package com.calvintd.kade.soccerbase.fragment


import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.presenter.LeagueSchedulePastMatchPresenter
import com.calvintd.kade.soccerbase.presenter.LeagueScheduleUpcomingMatchPresenter
import com.calvintd.kade.soccerbase.view.LeagueScheduleUpcomingMatchesView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.toast
import retrofit2.HttpException

/**
 * A simple [Fragment] subclass.
 */
class LeagueScheduleUpcomingMatchesFragment : Fragment(), LeagueScheduleUpcomingMatchesView {
    private var textView: TextView? = null
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null
    private val presenter = LeagueScheduleUpcomingMatchPresenter(this)

    companion object {
        fun newInstance(): LeagueScheduleUpcomingMatchesFragment = LeagueScheduleUpcomingMatchesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)

                textView = textView {
                    padding = 32
                    textSize = 16f
                    visibility = View.GONE
                    gravity = Gravity.CENTER
                }.lparams(width = matchParent, height = wrapContent)

                progressBar = progressBar {
                    padding = 128
                }.lparams(width = matchParent, height = matchParent)

                scrollView {
                    recyclerView = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(context)
                        visibility = View.GONE
                    }
                }
            }

            presenter.loadMatchesByLeague(recyclerView, 4329, "EPL")
        }.view
    }

    override fun loadMatchesByLeague(league: String) {
        textView?.visibility = View.VISIBLE
        textView?.text = String.format(
            resources.getString(R.string.league_schedule_display_upcoming_matches),
            league)
        recyclerView?.adapter?.notifyDataSetChanged()
        progressBar?.visibility = View.GONE
        recyclerView?.visibility = View.VISIBLE
    }

    override fun showNoResultsFound(league: String) {
        textView?.visibility = View.VISIBLE
        textView?.text = String.format(
            resources.getString(R.string.league_schedule_no_upcoming_matches),
            league)
        recyclerView?.adapter?.notifyDataSetChanged()
        progressBar?.visibility = View.GONE
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
    }

    override fun showException(e: HttpException) {
        toast(String.format(resources.getString(R.string.error_messages_http_exception), e.message()))
    }
}
