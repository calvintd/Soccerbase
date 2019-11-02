package com.calvintd.kade.soccerbase.activity.listing

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ListingsNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val buttonIconPadding = 16
        val buttonTextSize = 20f
        val headerIconSize = 480

        supportActionBar?.title = resources.getString(R.string.listings_navigation_activity_title)

        constraintLayout {
            lparams(width = matchParent, height = matchParent)

            val header = verticalLayout {
                id = R.id.llListingsNavigationLayout
                lparams(width = wrapContent, height = wrapContent)
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivListingsNavigationIcon
                    padding = 96
                    image = resources.getDrawable(R.drawable.ic_list_black_48dp, theme)
                }.lparams(width = headerIconSize, height = headerIconSize)

                textView {
                    id = R.id.tvListingsNavigationName
                    text = resources.getString(R.string.listings_navigation_title)
                    textSize = 40f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = matchConstraint, height = wrapContent)

            val leagueButton = linearLayout {
                id = R.id.llListingsNavigationLeagueLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivListingsNavigationLeagueIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.league, theme)
                }.lparams(width = wrapContent, height = wrapContent)

                textView {
                    id = R.id.tvListingsNavigationTeamName
                    text = resources.getString(R.string.listings_navigation_league)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<LeagueListingActivity>()
                }
            }.lparams(width = matchConstraint, height = wrapContent)

            val teamButton = linearLayout {
                id = R.id.llListingsNavigationTeamLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivListingsNavigationTeamIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.team, theme)
                }.lparams(width = wrapContent, height = wrapContent)

                textView {
                    id = R.id.tvListingsNavigationTeamName
                    text = resources.getString(R.string.listings_navigation_team)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<TeamListingActivity>()
                }
            }.lparams(width = matchConstraint, height = wrapContent)

            applyConstraintSet {
                val parent = ConstraintSet.PARENT_ID
                val start = ConstraintSetBuilder.Side.START
                val end = ConstraintSetBuilder.Side.END
                val top = ConstraintSetBuilder.Side.TOP
                val bottom = ConstraintSetBuilder.Side.BOTTOM
                val marginHeader = 64
                val marginButton = 32
                val marginButtonSeparator = 8

                header {
                    connect (
                        start to start of parent margin dip(marginHeader),
                        end to end of parent margin dip(marginHeader),
                        top to top of parent margin dip(marginHeader)
                    )
                }

                leagueButton {
                    connect (
                        start to start of parent margin dip(marginButton),
                        end to end of parent margin dip(marginButton),
                        top to bottom of header margin dip(marginHeader)
                    )
                }

                teamButton {
                    connect (
                        start to start of parent margin dip(marginButton),
                        end to end of parent margin dip(marginButton),
                        top to bottom of leagueButton margin dip(marginButtonSeparator)
                    )
                }
            }
        }
    }
}