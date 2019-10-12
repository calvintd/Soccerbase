package com.calvintd.kade.soccerbase.adapter

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.model.Match
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.*

class MatchAdapter (private val matches: List<Match>) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(MatchUI().createView(AnkoContext.Companion.create(parent.context, parent)))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(matches[position])

    override fun getItemCount(): Int = matches.size

    class MatchUI : AnkoComponent<ViewGroup> {
        companion object {
            val teamNameSize = 20f
            val scoreSize = 32f
            val teamPadding = 16
            val badgeSize = 128
        }

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            cardView {
                lparams(width = matchParent, height = wrapContent)
                radius = 4f
                elevation = 4f

                constraintLayout {
                    lparams(width = matchParent, height = wrapContent)

                    // general
                    val matchDateTime = textView {
                        id = R.id.tvMatchDateTime
                        text = resources.getString(R.string.item_event_date_time_placeholder)
                    }.lparams(width = matchConstraint, height = wrapContent)

                    val matchShowDetails = textView {
                        id = R.id.tvMatchShowDetails
                        text = resources.getString(R.string.match_listing_show_match_details)
                    }

                    // home
                    val homeTeam = verticalLayout {
                        lparams(width = wrapContent, height = wrapContent)
                        gravity = Gravity.CENTER

                        imageView {
                            id = R.id.ivHomeBadge
                            image = resources.getDrawable(R.drawable.soccer, context.theme)
                            padding = teamPadding
                        }.lparams(width = badgeSize, height = badgeSize)

                        textView {
                            id = R.id.tvHomeName
                            text = resources.getString(R.string.item_home_name_placeholder)
                            textSize = teamNameSize
                        }.lparams(width = wrapContent, height = wrapContent)
                    }

                    val homeScore = textView {
                        id = R.id.tvHomeScore
                        text = resources.getString(R.string.item_home_score_placeholder)
                        textSize = scoreSize
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)

                    // away
                    val awayTeam = verticalLayout {
                        lparams(width = wrapContent, height = wrapContent)
                        gravity = Gravity.CENTER

                        imageView {
                            id = R.id.ivAwayBadge
                            image = resources.getDrawable(R.drawable.soccer, context.theme)
                            padding = teamPadding
                        }.lparams(width = badgeSize, height = badgeSize)

                        textView {
                            id = R.id.tvAwayName
                            text = resources.getString(R.string.item_away_name_placeholder)
                            textSize = teamNameSize
                        }.lparams(width = wrapContent, height = wrapContent)
                    }

                    val awayScore = textView {
                        id = R.id.tvAwayScore
                        text = resources.getString(R.string.item_away_score_placeholder)
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

                        matchDateTime {
                            connect (
                                start to start of parent margin dip(margin),
                                end to end of parent margin dip(margin),
                                top to top of parent margin dip(margin)
                            )
                        }

                        homeTeam {
                            connect (
                                start to start of parent margin dip(margin),
                                top to bottom of matchDateTime margin dip(margin)
                            )
                        }

                        homeScore {
                            connect (
                                start to end of homeTeam margin dip(margin)
                            )
                        }

                        awayTeam {
                            connect (
                                end to end of parent margin dip(margin),
                                top to bottom of matchDateTime margin dip(margin)
                            )
                        }

                        awayScore {
                            connect (
                                end to start of awayTeam margin dip(margin)
                            )
                        }

                        matchShowDetails {
                            connect (
                                end to end of parent margin dip(margin),
                                bottom to bottom of parent margin dip(margin)
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
        private val resources = itemView.context.resources

        fun bindItem (match: Match) {
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
            homeScore.text = match.homeScore.toString()

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
            awayScore.text = match.awayScore.toString()
        }
    }
}