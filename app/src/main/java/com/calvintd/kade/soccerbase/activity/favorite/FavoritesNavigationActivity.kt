package com.calvintd.kade.soccerbase.activity.favorite

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

class FavoritesNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val buttonIconPadding = 16
        val buttonTextSize = 20f
        val headerIconSize = 432
        val menuIconSize = 128

        supportActionBar?.title = resources.getString(R.string.favorites_navigation_activity_title)

        constraintLayout {
            lparams(width = matchParent, height = matchParent)

            val header = verticalLayout {
                id = R.id.llFavoritesNavigationLayout
                lparams(width = wrapContent, height = wrapContent)
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivFavoritesNavigationIcon
                    padding = 48
                    image = resources.getDrawable(R.drawable.ic_favorited_black_48dp, theme)
                }.lparams(width = headerIconSize, height = headerIconSize)

                textView {
                    id = R.id.tvFavoritesNavigationName
                    text = resources.getString(R.string.favorites_navigation_title)
                    textSize = 40f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = matchConstraint, height = wrapContent)

            val matchesButton = linearLayout {
                id = R.id.llFavoritesNavigationMatchesLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivFavoritesNavigationMatchesIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.match, theme)
                }.lparams(width = menuIconSize, height = menuIconSize)

                textView {
                    id = R.id.tvListingsNavigationTeamName
                    text = resources.getString(R.string.favorite_navigation_matches)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<FavoriteMatchesActivity>()
                }
            }.lparams(width = matchConstraint, height = wrapContent)

            val teamsButton = linearLayout {
                id = R.id.llFavoritesNavigationTeamsLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivFavoritesNavigationTeamsIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.team, theme)
                }.lparams(width = menuIconSize, height = menuIconSize)

                textView {
                    id = R.id.tvFavoritesNavigationTeamsName
                    text = resources.getString(R.string.favorite_navigation_teams)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<FavoriteTeamsActivity>()
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

                matchesButton {
                    connect (
                        start to start of parent margin dip(marginButton),
                        end to end of parent margin dip(marginButton),
                        top to bottom of header margin dip(marginHeader)
                    )
                }

                teamsButton {
                    connect (
                        start to start of parent margin dip(marginButton),
                        end to end of parent margin dip(marginButton),
                        top to bottom of matchesButton margin dip(marginButtonSeparator)
                    )
                }
            }
        }
    }
}