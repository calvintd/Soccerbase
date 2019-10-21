package com.calvintd.kade.soccerbase.activity

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.model.Match
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*

class MatchDetailsActivity : AppCompatActivity() {
    private val teamNameSize = 12f
    private val scoreSize = 24f
    private val badgeSize = 96
    private val detailsTeamNameSize = 20f
    private val detailsBadgeSize = 160
    private val detailsPadding = 8
    private val horizontal = ConstraintLayout.LayoutParams.HORIZONTAL
    private val vertical = ConstraintLayout.LayoutParams.VERTICAL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val match = intent.getParcelableExtra("match") as Match
        val favoriteIconSize = 96

        scrollView {
            constraintLayout {
                lparams(width = matchParent, height = matchParent)
                padding = 16

                val favorite = linearLayout {
                    id = R.id.llMatchDetailsFavoriteLayout
                    lparams(width = wrapContent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL
                    rightPadding = 16
                    topPadding = 16

                    imageView {
                        id = R.id.ivMatchDetailsFavoriteIcon
                        image = resources.getDrawable(R.drawable.ic_favorite_black_48dp, context.theme)
                        padding = 8
                    }.lparams(width = favoriteIconSize, height = favoriteIconSize) {
                    }

                    textView {
                        id = R.id.tvMatchDetailsFavoriteName
                        text = resources.getString(R.string.match_details_favorite_match)
                        typeface = Typeface.DEFAULT_BOLD
                    }
                }

                val header = constraintLayout {
                    id = R.id.clMatchDetailsHeader
                    lparams(width = matchParent, height = wrapContent)

                    val centerGuideline = guideline {
                        id = R.id.glMatchDetailsHeaderCenterGuideline
                    }.lparams (width = 0, height = matchConstraint) {
                        orientation = vertical
                        guidePercent = 0.5f
                    }

                    // general
                    val matchDateTime = textView {
                        id = R.id.tvMatchDetailsDateTime
                        text = String.format(resources.getString(R.string.match_listing_date_time_format), match.matchDate, match.matchTime)
                        textSize = 14f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent) {
                        horizontalBias = 0.5f
                    }

                    val dateGuideline = guideline {
                        id = R.id.glMatchDetailsDateGuideline
                    }.lparams(width = matchConstraint, height = 0) {
                        orientation = horizontal
                    }

                    // home
                    val homeTeam = verticalLayout {
                        id = R.id.llHomeLayout
                        gravity = Gravity.CENTER

                        val homeBadge = imageView {
                            id = R.id.ivHomeBadge
                            image = resources.getDrawable(R.drawable.soccer, context.theme)
                        }.lparams(width = badgeSize, height = badgeSize)

                        match.homeBadge.let {
                            Picasso.get()
                                .load(it)
                                .fit()
                                .centerCrop()
                                .placeholder(R.drawable.ic_placeholder_black_48dp)
                                .error(R.drawable.ic_error_black_48dp)
                                .into(homeBadge)
                        }

                        textView {
                            id = R.id.tvHomeName
                            text = match.homeName
                            textSize = teamNameSize
                            gravity = Gravity.CENTER
                        }.lparams(width = wrapContent, height = wrapContent)
                    }.lparams(width = matchConstraint, height = wrapContent)

                    val homeScore = textView {
                        id = R.id.tvHomeScore
                        text = match.homeScore.toString()
                        textSize = scoreSize
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)

                    // away
                    val awayTeam = verticalLayout {
                        id = R.id.llAwayLayout
                        gravity = Gravity.CENTER

                        val awayBadge = imageView {
                            id = R.id.ivAwayBadge
                            image = resources.getDrawable(R.drawable.soccer, context.theme)
                        }.lparams(width = badgeSize, height = badgeSize)

                        match.awayBadge.let {
                            Picasso.get()
                                .load(it)
                                .fit()
                                .centerCrop()
                                .placeholder(R.drawable.ic_placeholder_black_48dp)
                                .error(R.drawable.ic_error_black_48dp)
                                .into(awayBadge)
                        }

                        textView {
                            id = R.id.tvAwayName
                            text = match.awayName
                            textSize = teamNameSize
                            gravity = Gravity.CENTER
                        }.lparams(width = wrapContent, height = wrapContent)
                    }.lparams(width = matchConstraint, height = wrapContent)

                    val awayScore = textView {
                        id = R.id.tvAwayScore
                        text = match.awayScore.toString()
                        textSize = scoreSize
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)

                    applyConstraintSet {
                        val parent = ConstraintSet.PARENT_ID
                        val start = ConstraintSetBuilder.Side.START
                        val end = ConstraintSetBuilder.Side.END
                        val top = ConstraintSetBuilder.Side.TOP
                        val bottom = ConstraintSetBuilder.Side.BOTTOM
                        val margin = 16

                        centerGuideline {
                            connect(
                                start to start of parent,
                                end to end of parent
                            )
                        }

                        matchDateTime {
                            connect(
                                start to start of parent margin dip(margin),
                                end to end of parent margin dip(margin),
                                top to top of parent margin dip(margin)
                            )
                        }

                        dateGuideline {
                            connect(
                                top to bottom of matchDateTime margin dip(margin)
                            )
                        }

                        homeTeam {
                            connect(
                                start to start of parent margin dip(margin),
                                end to start of homeScore margin dip(margin),
                                top to bottom of dateGuideline margin dip(margin)
                            )
                        }

                        homeScore {
                            connect(
                                end to start of centerGuideline margin dip(margin),
                                top to bottom of dateGuideline margin dip(margin)
                            )
                        }

                        awayTeam {
                            connect(
                                start to end of awayScore margin dip(margin),
                                end to end of parent margin dip(margin),
                                top to bottom of dateGuideline margin dip(margin)
                            )
                        }

                        awayScore {
                            connect(
                                start to end of centerGuideline margin dip(margin),
                                top to bottom of dateGuideline margin dip(margin)
                            )
                        }
                    }
                }

                val homeGuideline = guideline {
                    id = R.id.glMatchDetailsHomeGuideline
                }.lparams(width = matchConstraint, height = 0) {
                    orientation = horizontal
                }

                // home
                val homeDetails = constraintLayout {
                    id = R.id.clMatchDetailsHome
                    lparams(width = matchParent, height = wrapContent)
                    padding = 16

                    val homeTeamDetails = verticalLayout {
                        id = R.id.llMatchDetailsHomeTeamDetails
                        gravity = Gravity.CENTER

                        val homeBadge = imageView {
                            id = R.id.ivMatchDetailsHomeBadgeDetails
                            image = resources.getDrawable(R.drawable.soccer, context.theme)
                        }.lparams(width = detailsBadgeSize, height = detailsBadgeSize)

                        match.homeBadge.let {
                            Picasso.get()
                                .load(it)
                                .fit()
                                .centerCrop()
                                .placeholder(R.drawable.ic_placeholder_black_48dp)
                                .error(R.drawable.ic_error_black_48dp)
                                .into(homeBadge)
                        }

                        textView {
                            id = R.id.tvMatchDetailsHomeNameDetails
                            text = match.homeName
                            textSize = detailsTeamNameSize
                            gravity = Gravity.CENTER
                            topPadding = detailsPadding
                        }.lparams(width = wrapContent, height = wrapContent)                        
                    }.lparams(width = matchConstraint, height = wrapContent)
                    
                    val homeGoals = textView {
                        id = R.id.tvMatchDetailsHomeGoals
                        text = String.format(resources.getString(R.string.match_details_goals),
                            match.homeGoalDetails.joinToString(separator = ", ").trim())
                    }.lparams(width = wrapContent, height = wrapContent)
                    
                    val homeRedCards =  textView {
                        id = R.id.tvMatchDetailsHomeRedCards
                        text = String.format(resources.getString(R.string.match_details_red_cards),
                            match.homeRedCardDetails.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)
                    
                    val homeYellowCards = textView {
                        id = R.id.tvMatchDetailsHomeYellowCards
                        text = String.format(resources.getString(R.string.match_details_yellow_cards),
                            match.homeYellowCardDetails.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)
                    
                    val homeGoalkeeper = textView {
                        id = R.id.tvMatchDetailsHomeGoalkeeper
                        text = String.format(resources.getString(R.string.match_details_goalkeeper),
                            match.homeGoalkeeper.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)
                    
                    val homeDefenders = textView {
                        id = R.id.tvMatchDetailsHomeDefenders
                        text = String.format(resources.getString(R.string.match_details_defenders),
                            match.homeDefense.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)
                    
                    val homeMidfielders = textView {
                        id = R.id.tvMatchDetailsHomeMidfielders
                        text = String.format(resources.getString(R.string.match_details_midfielders),
                            match.homeMidfield.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)
                    
                    val homeForwards = textView {
                        id = R.id.tvMatchDetailsHomeForwards
                        text = String.format(resources.getString(R.string.match_details_forwards),
                            match.homeForward.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)
                    
                    val homeSubstitutes = textView {
                        id = R.id.tvMatchDetailsHomeSubstitutes
                        text = String.format(resources.getString(R.string.match_details_substitutes),
                            match.homeSubstitutes.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    applyConstraintSet {
                        val parent = ConstraintSet.PARENT_ID
                        val start = ConstraintSetBuilder.Side.START
                        val end = ConstraintSetBuilder.Side.END
                        val top = ConstraintSetBuilder.Side.TOP
                        val bottom = ConstraintSetBuilder.Side.BOTTOM
                        val margin = 16

                        homeTeamDetails {
                            connect (
                                start to start of parent,
                                end to end of parent,
                                top to top of homeGuideline margin dip(margin)
                            )
                        }
                        
                        homeGoals {
                            connect (
                                start to start of parent,
                                top to bottom of homeTeamDetails margin dip(margin)
                            )
                        }

                        homeRedCards {
                            connect (
                                start to start of parent,
                                top to bottom of homeGoals
                            )
                        }

                        homeYellowCards {
                            connect (
                                start to start of parent,
                                top to bottom of homeRedCards
                            )
                        }

                        homeGoalkeeper {
                            connect (
                                start to start of parent,
                                top to bottom of homeYellowCards
                            )
                        }

                        homeDefenders {
                            connect (
                                start to start of parent,
                                top to bottom of homeGoalkeeper
                            )
                        }

                        homeMidfielders {
                            connect (
                                start to start of parent,
                                top to bottom of homeDefenders
                            )
                        }

                        homeForwards {
                            connect (
                                start to start of parent,
                                top to bottom of homeMidfielders
                            )
                        }

                        homeSubstitutes {
                            connect (
                                start to start of parent,
                                top to bottom of homeForwards
                            )
                        }
                    }
                }

                val awayGuideline = guideline {
                    id = R.id.glMatchDetailsAwayGuideline
                }.lparams(width = matchConstraint, height = 0) {
                    orientation = horizontal
                }

                //away
                val awayDetails = constraintLayout {
                    id = R.id.clMatchDetailsAway
                    lparams(width = matchParent, height = wrapContent)
                    padding = 16

                    val awayTeamDetails = verticalLayout {
                        id = R.id.llMatchDetailsAwayTeamDetails
                        gravity = Gravity.CENTER

                        val homeBadge = imageView {
                            id = R.id.ivMatchDetailsAwayBadgeDetails
                            image = resources.getDrawable(R.drawable.soccer, context.theme)
                        }.lparams(width = detailsBadgeSize, height = detailsBadgeSize)

                        match.awayBadge.let {
                            Picasso.get()
                                .load(it)
                                .fit()
                                .centerCrop()
                                .placeholder(R.drawable.ic_placeholder_black_48dp)
                                .error(R.drawable.ic_error_black_48dp)
                                .into(homeBadge)
                        }

                        textView {
                            id = R.id.tvMatchDetailsAwayNameDetails
                            text = match.awayName
                            textSize = detailsTeamNameSize
                            gravity = Gravity.CENTER
                            topPadding = detailsPadding
                        }.lparams(width = wrapContent, height = wrapContent)
                    }.lparams(width = matchConstraint, height = wrapContent)

                    val awayGoals = textView {
                        id = R.id.tvMatchDetailsAwayGoals
                        text = String.format(resources.getString(R.string.match_details_goals),
                            match.awayGoalDetails.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    val awayRedCards =  textView {
                        id = R.id.tvMatchDetailsAwayRedCards
                        text = String.format(resources.getString(R.string.match_details_red_cards),
                            match.awayRedCardDetails.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    val awayYellowCards = textView {
                        id = R.id.tvMatchDetailsAwayYellowCards
                        text = String.format(resources.getString(R.string.match_details_yellow_cards),
                            match.awayYellowCardDetails.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    val awayGoalkeeper = textView {
                        id = R.id.tvMatchDetailsAwayGoalkeeper
                        text = String.format(resources.getString(R.string.match_details_goalkeeper),
                            match.awayGoalkeeper.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    val awayDefenders = textView {
                        id = R.id.tvMatchDetailsAwayDefenders
                        text = String.format(resources.getString(R.string.match_details_defenders),
                            match.awayDefense.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    val awayMidfielders = textView {
                        id = R.id.tvMatchDetailsAwayMidfielders
                        text = String.format(resources.getString(R.string.match_details_midfielders),
                            match.awayMidfield.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    val awayForwards = textView {
                        id = R.id.tvMatchDetailsAwayForwards
                        text = String.format(resources.getString(R.string.match_details_forwards),
                            match.awayForward.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    val awaySubstitutes = textView {
                        id = R.id.tvMatchDetailsAwaySubstitutes
                        text = String.format(resources.getString(R.string.match_details_substitutes),
                            match.awaySubstitutes.joinToString(separator = ", "))
                    }.lparams(width = wrapContent, height = wrapContent)

                    applyConstraintSet {
                        val parent = ConstraintSet.PARENT_ID
                        val start = ConstraintSetBuilder.Side.START
                        val end = ConstraintSetBuilder.Side.END
                        val top = ConstraintSetBuilder.Side.TOP
                        val bottom = ConstraintSetBuilder.Side.BOTTOM
                        val margin = 16

                        awayTeamDetails {
                            connect (
                                start to start of parent,
                                end to end of parent,
                                top to top of awayGuideline margin dip(margin)
                            )
                        }

                        awayGoals {
                            connect (
                                start to start of parent,
                                top to bottom of awayTeamDetails margin dip(margin)
                            )
                        }

                        awayRedCards {
                            connect (
                                start to start of parent,
                                top to bottom of awayGoals
                            )
                        }

                        awayYellowCards {
                            connect (
                                start to start of parent,
                                top to bottom of awayRedCards
                            )
                        }

                        awayGoalkeeper {
                            connect (
                                start to start of parent,
                                top to bottom of awayYellowCards
                            )
                        }

                        awayDefenders {
                            connect (
                                start to start of parent,
                                top to bottom of awayGoalkeeper
                            )
                        }

                        awayMidfielders {
                            connect (
                                start to start of parent,
                                top to bottom of awayDefenders
                            )
                        }

                        awayForwards {
                            connect (
                                start to start of parent,
                                top to bottom of awayMidfielders
                            )
                        }

                        awaySubstitutes {
                            connect (
                                start to start of parent,
                                top to bottom of awayForwards
                            )
                        }
                    }
                }

                applyConstraintSet {
                    val parent = ConstraintSet.PARENT_ID
                    val start = ConstraintSetBuilder.Side.START
                    val end = ConstraintSetBuilder.Side.END
                    val top = ConstraintSetBuilder.Side.TOP
                    val bottom = ConstraintSetBuilder.Side.BOTTOM
                    val margin = 16

                    favorite {
                        connect (
                            end to end of parent,
                            top to top of parent
                        )
                    }

                    header {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of favorite
                        )
                    }

                    homeGuideline {
                        connect (
                            top to bottom of header margin dip(margin)
                        )
                    }

                    homeDetails {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of homeGuideline margin dip(margin)
                        )
                    }
                    
                    awayGuideline {
                        connect (
                            top to bottom of homeDetails margin dip(margin)
                        )
                    }

                    awayDetails {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of awayGuideline margin dip(margin)
                        )
                    }
                }
            }
        }
    }
}