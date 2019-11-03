package com.calvintd.kade.soccerbase.activity.search

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.sdk27.coroutines.onClick

class SearchNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val buttonIconPadding = 16
        val buttonTextSize = 20f
        val headerIconSize = 432
        val menuIconSize = 128

        supportActionBar?.title = resources.getString(R.string.search_navigation_activity_title)

        constraintLayout {
            lparams(width = matchParent, height = matchParent)

            val header = verticalLayout {
                id = R.id.llSearchNavigationLayout
                lparams(width = wrapContent, height = wrapContent)
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivSearchNavigationIcon
                    padding = 48
                    image = resources.getDrawable(R.drawable.ic_search_black_48dp, theme)
                }.lparams(width = headerIconSize, height = headerIconSize)

                textView {
                    id = R.id.tvSearchNavigationName
                    text = resources.getString(R.string.search_navigation_title)
                    textSize = 40f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = matchConstraint, height = wrapContent)

            val matchButton = linearLayout {
                id = R.id.llSearchNavigationMatchLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivSearchNavigationMatchIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.match, theme)
                }.lparams(width = menuIconSize, height = menuIconSize)

                textView {
                    id = R.id.tvSearchNavigationMatchName
                    text = resources.getString(R.string.search_navigation_match)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<MatchSearchActivity>()
                }
            }.lparams(width = matchConstraint, height = wrapContent)

            val teamButton = linearLayout {
                id = R.id.llSearchNavigationTeamLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivSearchNavigationTeamIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.team, theme)
                }.lparams(width = menuIconSize, height = menuIconSize)

                textView {
                    id = R.id.tvSearchNavigationTeamName
                    text = resources.getString(R.string.search_navigation_team)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<TeamSearchActivity>()
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

                matchButton {
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
                        top to bottom of matchButton margin dip(marginButtonSeparator)
                    )
                }
            }
        }
    }
}