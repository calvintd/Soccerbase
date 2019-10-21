package com.calvintd.kade.soccerbase.activity

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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val buttonIconPadding = 16
        val buttonTextSize = 24f

        supportActionBar?.title = resources.getString(R.string.main_activity_title)

        constraintLayout {
            lparams(width = matchParent, height = matchParent)

            val header = verticalLayout {
                id = R.id.llAppLayout
                lparams(width = wrapContent, height = wrapContent)
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivAppLogo
                    padding = 64
                    image = resources.getDrawable(R.drawable.soccer, theme)
                }.lparams(width = 512, height = 512)

                textView {
                    id = R.id.tvAppName
                    text = resources.getString(R.string.main_activity_title)
                    textSize = 48f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = matchConstraint, height = wrapContent)

            val tagline = textView {
                id = R.id.tvTagline
                text = resources.getString(R.string.main_activity_tagline)
                textSize = 16f
                gravity = Gravity.CENTER
            }.lparams(width = wrapContent, height = wrapContent)

            val listingButton = linearLayout {
                id = R.id.llListingLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivListingIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.ic_list_black_48dp, theme)
                }.lparams(width = wrapContent, height = wrapContent)

                textView {
                    id = R.id.tvListingName
                    text = resources.getString(R.string.main_activity_league_listing_button)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<LeagueListingActivity>()
                }
            }.lparams(width = matchConstraint, height = wrapContent)

            val searchButton = linearLayout {
                id = R.id.llSearchLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivSearchIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.ic_search_black_48dp, theme)
                }.lparams(width = wrapContent, height = wrapContent)

                textView {
                    id = R.id.tvSearchName
                    text = resources.getString(R.string.main_activity_match_search_button)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<MatchSearchActivity>()
                }
            }.lparams(width = matchConstraint, height = wrapContent)

            val favoriteButton = linearLayout {
                id = R.id.llFavoriteLayout
                lparams(width = wrapContent, height = wrapContent)
                isClickable = true
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                imageView {
                    id = R.id.ivFavoriteIcon
                    padding = buttonIconPadding
                    image = resources.getDrawable(R.drawable.ic_not_favorite_black_48dp, theme)
                }.lparams(width = wrapContent, height = wrapContent)

                textView {
                    id = R.id.tvFavoriteName
                    text = resources.getString(R.string.main_activity_favorite_matches_button)
                    textSize = buttonTextSize
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent)

                onClick {
                    startActivity<FavoriteMatchesActivity>()
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

                tagline {
                    connect (
                        start to start of parent margin dip(marginHeader),
                        end to end of parent margin dip(marginHeader),
                        top to bottom of header
                    )
                }

                listingButton {
                    connect (
                        start to start of parent margin dip(marginButton),
                        end to end of parent margin dip(marginButton),
                        top to bottom of tagline margin dip(marginHeader)
                    )
                }

                searchButton {
                    connect (
                        start to start of parent margin dip(marginButton),
                        end to end of parent margin dip(marginButton),
                        top to bottom of listingButton margin dip(marginButtonSeparator)
                    )
                }

                favoriteButton {
                    connect (
                        start to start of parent margin dip(marginButton),
                        end to end of parent margin dip(marginButton),
                        top to bottom of searchButton margin dip(marginButtonSeparator)
                    )
                }
            }
        }
    }
}
