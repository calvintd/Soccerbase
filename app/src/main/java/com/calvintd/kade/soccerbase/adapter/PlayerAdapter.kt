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
import com.calvintd.kade.soccerbase.itemmodel.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayerAdapter (private val players: List<Player>, private val detailsListener: (Player) -> Unit) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(PlayerUI().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(players[position], detailsListener)

    override fun getItemCount(): Int = players.size

    class PlayerUI : AnkoComponent<ViewGroup> {
        companion object {
            const val iconSize = 40
            const val thumbSize = 160
            const val iconPadding = 6
            const val buttonTextSize = 12f
        }

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            constraintLayout {
                lparams(width = matchParent, height = wrapContent)

                val playerLayout = linearLayout {
                    id = R.id.llPlayerLayout
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    imageView {
                        id = R.id.ivPlayerThumb
                        image =
                            resources.getDrawable(R.drawable.ic_placeholder_black_48dp, ctx.theme)
                        rightPadding = 32
                    }.lparams(width = thumbSize, height = thumbSize)

                    textView {
                        id = R.id.tvPlayerName
                        text = resources.getText(R.string.item_player_name_placeholder)
                        textSize = 18f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = matchConstraint, height = wrapContent)

                val buttonGuideline = guideline {
                    id = R.id.glPlayerButtonGuideline
                }.lparams(width = matchConstraint, height = 0) {
                    orientation = ConstraintLayout.LayoutParams.HORIZONTAL
                }

                val detailsButton = linearLayout {
                    id = R.id.llPlayerDetailsLayout
                    lparams(width = wrapContent, height = wrapContent)
                    isClickable = true
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL

                    imageView {
                        id = R.id.ivPlayerDetailsIcon
                        image = resources.getDrawable(R.drawable.ic_description_black_48dp, context.theme)
                        rightPadding = iconPadding
                    }.lparams(width = iconSize, height = iconSize)

                    textView {
                        id = R.id.tvPlayerDetailsName
                        text = resources.getString(R.string.player_listing_details_button)
                        textSize = buttonTextSize
                        textColor = Color.rgb(196, 77, 255)
                    }.lparams(width = wrapContent, height = wrapContent)
                }

                applyConstraintSet {
                    val parent = ConstraintSet.PARENT_ID
                    val start = ConstraintSetBuilder.Side.START
                    val end = ConstraintSetBuilder.Side.END
                    val top = ConstraintSetBuilder.Side.TOP
                    val bottom = ConstraintSetBuilder.Side.BOTTOM
                    val margin = 16

                    playerLayout {
                        connect (
                            start to start of parent margin dip(margin),
                            end to end of parent margin dip(margin)
                        )
                    }

                    buttonGuideline {
                        connect (
                            top to bottom of playerLayout margin dip(margin)
                        )
                    }

                    detailsButton {
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
        private val thumb = view.find<ImageView>(R.id.ivPlayerThumb)
        private val name = view.find<TextView>(R.id.tvPlayerName)
        private val detailsButton = view.find<LinearLayout>(R.id.llPlayerDetailsLayout)

        fun bindItem (player: Player, detailsListener: (Player) -> Unit) {
            player.playerThumbnail.let {
                Picasso.get()
                    .load(it)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder_black_48dp)
                    .error(R.drawable.ic_error_black_48dp)
                    .into(thumb)
            }

            name.text = player.playerName

            detailsButton.onClick {
                detailsListener(player)
            }
        }
    }
}