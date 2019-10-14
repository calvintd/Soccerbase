package com.calvintd.kade.soccerbase.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*
import org.jetbrains.anko.design.bottomNavigationView

class LeagueScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        }
    }
}