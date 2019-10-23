package com.calvintd.kade.soccerbase.fragment

import android.os.Bundle
import android.os.Parcelable
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
import com.calvintd.kade.soccerbase.activity.MatchDetailsActivity
import com.calvintd.kade.soccerbase.adapter.MatchAdapter
import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.presenter.LeagueSchedulePastMatchesPresenter
import com.calvintd.kade.soccerbase.view.LeagueSchedulePastMatchesView
import okhttp3.ResponseBody
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.toast
import retrofit2.HttpException

/**
 * A simple [Fragment] subclass.
 */
class LeagueSchedulePastMatchesFragment : Fragment(), LeagueSchedulePastMatchesView {
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private val presenter = LeagueSchedulePastMatchesPresenter(this)

    companion object {
        private const val LEAGUE_BUNDLE_ARG = "league"

        fun newInstance(bundleName: Parcelable): LeagueSchedulePastMatchesFragment {
            val fragment = LeagueSchedulePastMatchesFragment()
            val bundle = Bundle().apply {
                putParcelable(LEAGUE_BUNDLE_ARG, bundleName)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val league = arguments?.getParcelable(LEAGUE_BUNDLE_ARG) as League
        val leagueId = league.leagueId
        val leagueName = league.name

        return UI {
            verticalLayout {
                lparams(width = matchParent, height = wrapContent)

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

            presenter.loadMatchesByLeague(leagueId!!, leagueName!!)
        }.view
    }

    override fun loadMatchesByLeague(matches: ArrayList<Match>, league: String) {
        if (context != null) {
            textView.visibility = View.VISIBLE
            textView.text = String.format(
                resources.getString(R.string.league_schedule_display_past_matches),
                league)
            recyclerView.adapter = MatchAdapter(matches) {
                context?.startActivity<MatchDetailsActivity>(
                    "match" to it
                )
            }
            recyclerView.adapter!!.notifyDataSetChanged()
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun showNoResultsFound(league: String) {
        if (context != null) {
            textView.visibility = View.VISIBLE
            textView.text = String.format(
                resources.getString(R.string.league_schedule_no_past_matches),
                league)
            recyclerView.adapter?.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        }
    }

    override fun showResponseError(code: Int, responseBody: ResponseBody?) {
        toast(String.format(resources.getString(R.string.error_messages_response_code), code.toString(), responseBody.toString()))
    }

    override fun showException(e: HttpException) {
        toast(String.format(resources.getString(R.string.error_messages_http_exception), e.message()))
    }
}
