package com.calvintd.kade.soccerbase.adapter

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.Standings
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class StandingsAdapter (private val standings: List<Standings>) : RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(StandingsUI().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(standings[position])

    override fun getItemCount(): Int = standings.size

    class StandingsUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            constraintLayout {
                lparams(width = matchParent, height = wrapContent)

                val position = textView {
                    id = R.id.tvLeagueStandingsPosition
                    padding = 16
                    textSize = 24f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent) {
                    verticalBias = 0.5f
                }

                val name = textView {
                    id = R.id.tvLeagueStandingsName
                    padding = 8
                    textSize = 16f
                }.lparams(width = wrapContent, height = wrapContent) {
                    verticalBias = 0.5f
                }

                val infoBox = verticalLayout {
                    id = R.id.llLeagueStandingsInfoBoxLayout
                    padding = 8
                    gravity = Gravity.START

                    textView {
                        id = R.id.tvLeagueStandingsWDL
                        textSize = 10f
                    }.lparams(width = wrapContent, height = wrapContent)

                    textView {
                        id = R.id.tvLeagueStandingsGoals
                        textSize = 10f
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = wrapContent, height = wrapContent)

                val totalPoints = textView {
                    id = R.id.tvLeagueStandingsTotalPoints
                    padding = 16
                    textSize = 32f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = wrapContent, height = wrapContent) {
                    verticalBias = 0.5f
                }

                applyConstraintSet {
                    val parent = ConstraintSet.PARENT_ID
                    val start = ConstraintSetBuilder.Side.START
                    val end = ConstraintSetBuilder.Side.END
                    val top = ConstraintSetBuilder.Side.TOP
                    val bottom = ConstraintSetBuilder.Side.BOTTOM
                    val margin = 8

                    position {
                        connect (
                            start to start of parent,
                            top to top of parent,
                            bottom to bottom of parent
                        )
                    }

                    name {
                        connect (
                            start to end of position margin dip(margin),
                            top to top of parent,
                            bottom to bottom of parent
                        )
                    }

                    infoBox {
                        connect (
                            end to start of totalPoints margin dip(margin),
                            top to top of parent,
                            bottom to bottom of parent
                        )
                    }

                    totalPoints {
                        connect (
                            end to end of parent,
                            top to top of parent,
                            bottom to bottom of parent
                        )
                    }
                }
            }
        }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        private val position = view.find<TextView>(R.id.tvLeagueStandingsPosition)
        private val name = view.find<TextView>(R.id.tvLeagueStandingsName)
        private val wdl = view.find<TextView>(R.id.tvLeagueStandingsWDL)
        private val goals = view.find<TextView>(R.id.tvLeagueStandingsGoals)
        private val totalPoints = view.find<TextView>(R.id.tvLeagueStandingsTotalPoints)

        fun bindItem (standings: Standings) {
            position.text = adapterPosition.plus(1).toString()
            name.text = standings.name
            wdl.text = String.format(wdl.context.resources.getString(R.string.league_standings_wdl),
                standings.wins, standings.draws, standings.losses)
            goals.text = String.format(goals.context.resources.getString(R.string.league_standings_goals),
                standings.goalsFor, standings.goalsAgainst, standings.goalsDifference)
            totalPoints.text = standings.points.toString()
        }
    }
}