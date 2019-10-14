package com.calvintd.kade.soccerbase.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.fragment.LeagueSchedulePastMatchesFragment
import com.calvintd.kade.soccerbase.fragment.LeagueScheduleUpcomingMatchesFragment
import com.calvintd.kade.soccerbase.model.League
import com.calvintd.kade.soccerbase.presenter.LeagueSchedulePastMatchPresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*
import org.jetbrains.anko.design.bottomNavigationView

class LeagueScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val league = intent.getParcelableExtra("league") as League

        supportActionBar?.title = resources.getString(R.string.league_schedule_activity_title)

        constraintLayout {
            lparams(width = matchParent, height = matchParent)

            val scheduleFrame = frameLayout {
                id = R.id.flScheduleFrame
            }.lparams(width = matchConstraint, height = wrapContent)

            val scheduleNav = bottomNavigationView {
                id = R.id.bnScheduleNav
                inflateMenu(R.menu.bottom_navigation)
            }.lparams(width = matchConstraint, height = wrapContent)

            applyConstraintSet {
                val parent = ConstraintSet.PARENT_ID
                val start = ConstraintSetBuilder.Side.START
                val end = ConstraintSetBuilder.Side.END
                val top = ConstraintSetBuilder.Side.TOP
                val bottom = ConstraintSetBuilder.Side.BOTTOM

                scheduleFrame {
                    connect (
                        start to start of parent,
                        end to end of parent,
                        top to top of parent,
                        bottom to top of scheduleNav
                    )
                }

                scheduleNav {
                    connect (
                        start to start of parent,
                        end to end of parent,
                        bottom to bottom of parent
                    )
                }
            }

            scheduleNav.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_past -> {
                        val fragment = LeagueSchedulePastMatchesFragment.newInstance()
                        switchFragment(fragment)
                    }
                    R.id.nav_upcoming -> {
                        val fragment = LeagueScheduleUpcomingMatchesFragment.newInstance()
                        switchFragment(fragment)
                    }
                }
                false
            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flScheduleFrame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}