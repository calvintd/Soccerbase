package com.calvintd.kade.thesportdb.recyclerview

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.thesportdb.R
import com.calvintd.kade.thesportdb.activity.DetailActivity
import com.calvintd.kade.thesportdb.model.League
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class LeagueAdapter(private val items: List<League>)
    : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LeagueUI().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    class LeagueUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            constraintLayout {
                lparams(width = matchParent, height = wrapContent)

                val badge = imageView {
                    id = R.id.ivListBadge
                    image = resources.getDrawable(R.drawable.ic_placeholder_black_48dp, ctx.theme)
                }.lparams(width = wrapContent, height = wrapContent)

                val name = textView {
                    id = R.id.tvListName
                    text = resources.getText(R.string.item_league_name_placeholder)
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD

                }.lparams(width = matchConstraint, height = wrapContent)

                applyConstraintSet {
                    val parent = ConstraintSet.PARENT_ID
                    val start = ConstraintSetBuilder.Side.START
                    val end = ConstraintSetBuilder.Side.END
                    val top = ConstraintSetBuilder.Side.TOP
                    val bottom = ConstraintSetBuilder.Side.BOTTOM
                    val margin = 16

                    badge {
                        connect (
                            start to start of parent margin dip(margin),
                            top to top of parent margin dip(margin),
                            bottom to bottom of parent margin dip(margin)
                        )
                    }
                    name {
                        connect (
                            start to end of badge margin dip(margin),
                            end to end of parent margin dip(margin),
                            top to top of parent margin dip(margin),
                            bottom to bottom of parent margin dip(margin)
                        )
                    }
                }
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val badge = view.findViewById<ImageView>(R.id.ivListBadge)
        private val name = view.findViewById<TextView>(R.id.tvListName)

        fun bindItem(league: League) {
            league.badge.let { Picasso.get()
                .load(it)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_placeholder_black_48dp)
                .error(R.drawable.ic_error_black_48dp)
                .into(badge) }
            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>(
                    "league" to league
                )
            }
            name.text = league.name
        }
    }
}