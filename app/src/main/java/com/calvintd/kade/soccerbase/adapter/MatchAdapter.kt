package com.calvintd.kade.soccerbase.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.activity.MatchDetailsActivity
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MatchAdapter (private val matches: List<Match>) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(MatchUI().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(matches[position])

    override fun getItemCount(): Int = matches.size

    class MatchUI : AnkoComponent<ViewGroup> {
        companion object {
            const val teamNameSize = 12f
            const val scoreSize = 24f
            const val badgeSize = 96
            const val horizontal = ConstraintLayout.LayoutParams.HORIZONTAL
            const val vertical = ConstraintLayout.LayoutParams.VERTICAL
        }

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            cardView {
                lparams(width = matchParent, height = wrapContent)
                radius = 4f
                elevation = 4f

                constraintLayout {
                    lparams(width = matchParent, height = wrapContent)

                    val centerGuideline = guideline {
                        id = R.id.glMatchCenterGuideline
                    }.lparams (width = 0, height = matchConstraint) {
                        orientation = vertical
                        guidePercent = 0.5f
                    }

                    // general
                    val matchDateTime = textView {
                        id = R.id.tvMatchDateTime
                        text = resources.getString(R.string.item_event_date_time_placeholder)
                    }.lparams(width = wrapContent, height = wrapContent) {
                        horizontalBias = 0.5f
                    }

                    val dateGuideline = guideline {
                        id = R.id.glDateTimeGuideline
                    }.lparams(width = matchConstraint, height = 0) {
                        orientation = horizontal
                    }

                    // home
                    val homeTeam = verticalLayout {
                        id = R.id.llHomeLayout
                        gravity = Gravity.CENTER

                        imageView {
                            id = R.id.ivHomeBadge
                            image = resources.getDrawable(R.drawable.soccer, context.theme)
                        }.lparams(width = badgeSize, height = badgeSize)

                        textView {
                            id = R.id.tvHomeName
                            text = resources.getString(R.string.item_home_name_placeholder)
                            textSize = teamNameSize
                            gravity = Gravity.CENTER
                        }.lparams(width = wrapContent, height = wrapContent)
                    }.lparams(width = matchConstraint, height = wrapContent)

                    val homeScore = textView {
                        id = R.id.tvHomeScore
                        text = resources.getString(R.string.item_home_score_placeholder)
                        textSize = scoreSize
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)

                    // away
                    val awayTeam = verticalLayout {
                        id = R.id.llAwayLayout
                        gravity = Gravity.CENTER

                        imageView {
                            id = R.id.ivAwayBadge
                            image = resources.getDrawable(R.drawable.soccer, context.theme)
                        }.lparams(width = badgeSize, height = badgeSize)

                        textView {
                            id = R.id.tvAwayName
                            text = resources.getString(R.string.item_away_name_placeholder)
                            textSize = teamNameSize
                            gravity = Gravity.CENTER
                        }.lparams(width = wrapContent, height = wrapContent)
                    }.lparams(width = matchConstraint, height = wrapContent)

                    val awayScore = textView {
                        id = R.id.tvAwayScore
                        text = resources.getString(R.string.item_away_score_placeholder)
                        textSize = scoreSize
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)

                    val showDetailsGuideline = guideline {
                        id = R.id.glShowDetailsGuideline
                    }.lparams(width = matchConstraint, height = 0) {
                        orientation = horizontal
                    }

                    val matchShowDetails = textView {
                        id = R.id.tvMatchShowDetails
                        text = resources.getString(R.string.match_listing_show_match_details)
                        textSize = 12f
                        textColor = Color.BLUE
                    }.lparams(width = wrapContent, height = wrapContent)

                    applyConstraintSet {
                        val parent = ConstraintSet.PARENT_ID
                        val start = ConstraintSetBuilder.Side.START
                        val end = ConstraintSetBuilder.Side.END
                        val top = ConstraintSetBuilder.Side.TOP
                        val bottom = ConstraintSetBuilder.Side.BOTTOM
                        val margin = 16
                        val halfMargin = margin / 2

                        centerGuideline {
                            connect (
                                start to start of parent,
                                end to end of parent
                            )
                        }

                        matchDateTime {
                            connect (
                                start to start of parent margin dip(margin),
                                end to end of parent margin dip(margin),
                                top to top of parent margin dip(margin)
                            )
                        }

                        dateGuideline {
                            connect (
                                top to bottom of matchDateTime margin dip(margin)
                            )
                        }

                        homeTeam {
                            connect (
                                start to start of parent margin dip(margin),
                                end to start of homeScore margin dip(margin),
                                top to bottom of dateGuideline margin dip(margin)
                            )
                        }

                        homeScore {
                            connect (
                                end to start of centerGuideline margin dip(margin),
                                top to bottom of dateGuideline margin dip(margin)
                            )
                        }

                        awayTeam {
                            connect (
                                start to end of awayScore margin dip(margin),
                                end to end of parent margin dip(margin),
                                top to bottom of dateGuideline margin dip(margin)
                            )
                        }

                        awayScore {
                            connect (
                                start to end of centerGuideline margin dip(margin),
                                top to bottom of dateGuideline margin dip(margin)
                            )
                        }

                        showDetailsGuideline {
                            connect (
                                top to bottom of homeTeam margin dip(margin),
                                top to bottom of awayTeam margin dip(margin)
                            )
                        }

                        matchShowDetails {
                            connect (
                                end to end of parent margin dip(margin),
                                top to bottom of showDetailsGuideline margin dip(halfMargin),
                                bottom to bottom of parent
                            )
                        }
                    }
                }
            }
        }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private val matchDateTime = view.find<TextView>(R.id.tvMatchDateTime)
        private val homeBadge = view.find<ImageView>(R.id.ivHomeBadge)
        private val homeName = view.find<TextView>(R.id.tvHomeName)
        private val homeScore = view.find<TextView>(R.id.tvHomeScore)
        private val awayBadge = view.find<ImageView>(R.id.ivAwayBadge)
        private val awayName = view.find<TextView>(R.id.tvAwayName)
        private val awayScore = view.find<TextView>(R.id.tvAwayScore)
        private val matchShowDetails = view.find<TextView>(R.id.tvMatchShowDetails)
        private val resources = itemView.context.resources

        fun bindItem (match: Match) {
            val currentTime = LocalDateTime.now()
            val matchFetchedDateTime = LocalDateTime.of(LocalDate.parse(match.matchDate, DateTimeFormatter.ofPattern("d MMMM u")),
                LocalTime.parse(match.matchTime, DateTimeFormatter.ofPattern("HH:mm")))

            matchDateTime.text = String.format(resources.getString(R.string.match_listing_date_time_format), match.matchDate, match.matchTime)

            match.homeBadge.let {
                Picasso.get()
                .load(it)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder_black_48dp)
                .error(R.drawable.ic_error_black_48dp)
                .into(homeBadge)
            }

            homeName.text = match.homeName
            homeScore.text = (match.homeScore ?: "?").toString()

            match.awayBadge.let {
                Picasso.get()
                .load(it)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder_black_48dp)
                .error(R.drawable.ic_error_black_48dp)
                .into(awayBadge)
            }

            awayName.text = match.awayName
            awayScore.text = (match.awayScore ?: "?").toString()

            val blankScore = match.homeScore == null || match.awayScore == null

            if (currentTime < matchFetchedDateTime || blankScore) {
                 matchShowDetails.visibility = View.GONE
            } else {
                matchShowDetails.visibility = View.VISIBLE
            }

            matchShowDetails.onClick {
                itemView.context.startActivity<MatchDetailsActivity>(
                    "match" to match
                )
            }
        }
    }
}