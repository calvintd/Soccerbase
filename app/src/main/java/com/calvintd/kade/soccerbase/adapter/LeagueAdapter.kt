package com.calvintd.kade.soccerbase.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.activity.LeagueDescriptionActivity
import com.calvintd.kade.soccerbase.activity.LeagueScheduleActivity
import com.calvintd.kade.soccerbase.model.League
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class LeagueAdapter (private val leagues: List<League>) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LeagueUI().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(leagues[position])

    override fun getItemCount(): Int = leagues.size

    class LeagueUI : AnkoComponent<ViewGroup> {
        companion object {
            const val iconSize = 40
            const val badgeSize = 160
            const val iconPadding = 6
            const val buttonTextSize = 12f
        }

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            constraintLayout {
                lparams(width = matchParent, height = wrapContent)

                val leagueLayout = linearLayout {
                    id = R.id.llLeagueLayout
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    imageView {
                        id = R.id.ivLeagueBadge
                        image = resources.getDrawable(R.drawable.ic_placeholder_black_48dp, ctx.theme)
                        rightPadding = 32
                    }.lparams(width = badgeSize, height = badgeSize)

                    textView {
                        id = R.id.tvLeagueName
                        text = resources.getText(R.string.item_league_name_placeholder)
                        textSize = 18f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = matchConstraint, height = wrapContent)

                val buttonGuideline = guideline {
                    id = R.id.glButtonGuideline
                }.lparams(width = matchConstraint, height = 0) {
                    orientation = ConstraintLayout.LayoutParams.HORIZONTAL
                }

                val descriptionButton = linearLayout {
                    id = R.id.llListingDescriptionLayout
                    lparams(width = wrapContent, height = wrapContent)
                    isClickable = true
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    imageView {
                        id = R.id.ivListingDescriptionIcon
                        image = resources.getDrawable(R.drawable.ic_description_black_48dp, context.theme)
                        rightPadding = iconPadding
                    }.lparams(width = iconSize, height = iconSize)

                    textView {
                        id = R.id.tvListingDescriptionName
                        text = resources.getString(R.string.league_listing_description_button)
                        textSize = buttonTextSize
                        textColor = Color.MAGENTA
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = wrapContent, height = wrapContent)

                val scheduleButton = linearLayout {
                    id = R.id.llListingScheduleLayout
                    lparams(width = wrapContent, height = wrapContent)
                    isClickable = true
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    imageView {
                        id = R.id.ivListingScheduleIcon
                        image = resources.getDrawable(R.drawable.ic_date_black_48dp, context.theme)
                        rightPadding = iconPadding
                    }.lparams(width = iconSize, height = iconSize)

                    textView {
                        id = R.id.tvListingScheduleName
                        text = resources.getString(R.string.league_listing_schedule_button)
                        textSize = buttonTextSize
                        textColor = Color.rgb(129,182,157)
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = wrapContent, height = wrapContent)

                applyConstraintSet {
                    val parent = ConstraintSet.PARENT_ID
                    val start = ConstraintSetBuilder.Side.START
                    val end = ConstraintSetBuilder.Side.END
                    val top = ConstraintSetBuilder.Side.TOP
                    val bottom = ConstraintSetBuilder.Side.BOTTOM
                    val margin = 16

                    leagueLayout {
                        connect (
                            start to start of parent margin dip(margin),
                            end to end of parent margin dip(margin)
                        )
                    }

                    buttonGuideline {
                        connect (
                            top to bottom of leagueLayout margin dip(margin)
                        )
                    }

                    descriptionButton {
                        connect (
                            end to start of scheduleButton margin dip(margin),
                            top to bottom of buttonGuideline,
                            bottom to bottom of parent
                        )
                    }

                    scheduleButton {
                        connect (
                            end to end of parent margin dip(margin),
                            top to bottom of buttonGuideline,
                            bottom to bottom of parent
                        )
                    }
                }
            }
        }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        private val badge = view.find<ImageView>(R.id.ivLeagueBadge)
        private val name = view.find<TextView>(R.id.tvLeagueName)
        private val descriptionButton = view.find<LinearLayout>(R.id.llListingDescriptionLayout)
        private val scheduleButton = view.find<LinearLayout>(R.id.llListingScheduleLayout)

        fun bindItem (league: League) {
            league.badge.let {
                Picasso.get()
                .load(it)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder_black_48dp)
                .error(R.drawable.ic_error_black_48dp)
                .into(badge) }

            name.text = league.name

            descriptionButton.onClick {
                itemView.context.startActivity<LeagueDescriptionActivity>(
                    "league" to league
                )
            }

            scheduleButton.onClick {
                itemView.context.startActivity<LeagueScheduleActivity>(
                    "league" to league
                )
            }
        }
    }
}