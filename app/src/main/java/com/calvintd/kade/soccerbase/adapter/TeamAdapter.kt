package com.calvintd.kade.soccerbase.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.Team
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamAdapter (private val teams: List<Team>, private val detailsListener: (Team) -> Unit,
                   private val playersListener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(TeamUI().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(teams[position], detailsListener, playersListener)

    override fun getItemCount(): Int = teams.size

    class TeamUI : AnkoComponent<ViewGroup> {
        companion object {
            const val iconSize = 40
            const val badgeSize = 160
            const val iconPadding = 6
            const val buttonTextSize = 12f
        }

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            constraintLayout {
                lparams(width = matchParent, height = wrapContent)

                val teamLayout = linearLayout {
                    id = R.id.llTeamLayout
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    imageView {
                        id = R.id.ivLeagueBadge
                        image = resources.getDrawable(R.drawable.ic_placeholder_black_48dp, ctx.theme)
                        rightPadding = 32
                    }.lparams(width = badgeSize, height = badgeSize)

                    textView {
                        id = R.id.tvLeagueName
                        text = resources.getText(R.string.item_team_name_placeholder)
                        textSize = 18f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = matchConstraint, height = wrapContent)

                val buttonGuideline = guideline {
                    id = R.id.glLeagueButtonGuideline
                }.lparams(width = matchConstraint, height = 0) {
                    orientation = ConstraintLayout.LayoutParams.HORIZONTAL
                }

                val detailsButton = linearLayout {
                    id = R.id.llTeamDetailsLayout
                    lparams(width = wrapContent, height = wrapContent)
                    isClickable = true
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    imageView {
                        id = R.id.ivTeamDetailsIcon
                        image = resources.getDrawable(R.drawable.ic_description_black_48dp, context.theme)
                        rightPadding = iconPadding
                    }.lparams(width = iconSize, height = iconSize)

                    textView {
                        id = R.id.tvTeamDetailsName
                        text = resources.getString(R.string.team_listing_details_button)
                        textSize = buttonTextSize
                        textColor = Color.MAGENTA
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = wrapContent, height = wrapContent)

                val playersButton = linearLayout {
                    id = R.id.llTeamPlayersLayout
                    lparams(width = wrapContent, height = wrapContent)
                    isClickable = true
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    imageView {
                        id = R.id.ivTeamPlayersIcon
                        image = resources.getDrawable(R.drawable.player, context.theme)
                        rightPadding = iconPadding
                    }.lparams(width = iconSize, height = iconSize)

                    textView {
                        id = R.id.tvListingScheduleName
                        text = resources.getString(R.string.team_listing_players_button)
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

                    teamLayout {
                        connect (
                            start to start of parent margin dip(margin),
                            end to end of parent margin dip(margin)
                        )
                    }

                    buttonGuideline {
                        connect (
                            top to bottom of teamLayout margin dip(margin)
                        )
                    }

                    detailsButton {
                        connect (
                            end to start of playersButton margin dip(margin),
                            top to bottom of buttonGuideline,
                            bottom to bottom of parent
                        )
                    }

                    playersButton {
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

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val detailsButton = view.find<LinearLayout>(R.id.llTeamDetailsLayout)
        val playersButton = view.find<LinearLayout>(R.id.llTeamPlayersLayout)

        fun bindItem (team: Team, detailsListener: (Team) -> Unit, playersListener: (Team) -> Unit) {

            detailsButton.onClick {
                detailsListener(team)
            }

            playersButton.onClick {
                playersListener(team)
            }
        }
    }
}