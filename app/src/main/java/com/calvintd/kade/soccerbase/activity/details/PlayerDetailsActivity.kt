package com.calvintd.kade.soccerbase.activity.details

import android.graphics.Typeface
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.Player
import com.calvintd.kade.soccerbase.utils.matchutils.DateTimeFormatterUtil
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class PlayerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.player_details_activity_title)

        val player = intent.getParcelableExtra("player") as Player

        scrollView {
            constraintLayout {
                lparams(width = matchParent, height = matchParent)
                padding = 16

                val fanart = imageView {
                    id = R.id.ivPlayerDetailsActivityFanart
                }.lparams(width = matchConstraint, height = wrapContent)

                val thumb = imageView {
                    id = R.id.ivPlayerDetailsActivityThumb
                }.lparams(width = wrapContent, height = wrapContent)

                val name = textView {
                    id = R.id.tvPlayerDetailsActivityName
                    text = player.playerName
                    textSize = 20f
                    gravity = Gravity.CENTER
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = matchConstraint, height = wrapContent)

                val nationality = textView {
                    id = R.id.tvPlayerDetailsActivityNationality
                    text = String.format(resources.getString(R.string.player_details_nationality), player.playerNationality)
                }.lparams(width = wrapContent, height = wrapContent)

                val team = textView {
                    id = R.id.tvPlayerDetailsActivityTeam
                    text = String.format(resources.getString(R.string.player_details_team), player.playerTeam)
                }.lparams(width = wrapContent, height = wrapContent)

                val birthDate = textView {
                    id = R.id.tvPlayerDetailsActivityBirthDate
                    text = String.format(resources.getString(R.string.player_details_birth_date),
                        DateTimeFormatterUtil.dateFormat(player.playerBirthDate))
                }.lparams(width = wrapContent, height = wrapContent)

                val birthLocation = textView {
                    id = R.id.tvPlayerDetailsActivityBirthLocation
                    text = String.format(resources.getString(R.string.player_details_birth_location), player.playerBirthLocation)
                }.lparams(width = wrapContent, height = wrapContent)

                val signedDate = textView {
                    id = R.id.tvPlayerDetailsActivitySignedDate
                    text = String.format(resources.getString(R.string.player_details_signed_date),
                        DateTimeFormatterUtil.dateFormat(player.playerSignedDate))
                }.lparams(width = wrapContent, height = wrapContent)

                val position = textView {
                    id = R.id.tvPlayerDetailsActivityPosition
                    text = String.format(resources.getString(R.string.player_details_position), player.playerPosition)
                }.lparams(width = wrapContent, height = wrapContent)

                val description = textView {
                    id = R.id.tvPlayerDetailsActivityDescription
                    text = player.playerDescription
                }.lparams(width = matchConstraint, height = wrapContent)

                applyConstraintSet {
                    val parent = ConstraintSet.PARENT_ID
                    val start = ConstraintSetBuilder.Side.START
                    val end = ConstraintSetBuilder.Side.END
                    val top = ConstraintSetBuilder.Side.TOP
                    val bottom = ConstraintSetBuilder.Side.BOTTOM
                    val margin = 16
                    val doubleMargin = margin * 2
                    val halfMargin = margin / 2

                    fanart {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to top of parent
                        )
                    }

                    thumb {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of fanart margin dip(doubleMargin)
                        )
                    }

                    name {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of thumb margin dip(halfMargin)
                        )
                    }

                    nationality {
                        connect (
                            start to start of parent,
                            top to bottom of name margin dip(margin)
                        )
                    }

                    team {
                        connect (
                            start to start of parent,
                            top to bottom of nationality
                        )
                    }

                    birthDate {
                        connect (
                            start to start of parent,
                            top to bottom of team
                        )
                    }

                    birthLocation {
                        connect (
                            start to start of parent,
                            top to bottom of birthDate
                        )
                    }

                    signedDate {
                        connect (
                            start to start of parent,
                            top to bottom of birthLocation
                        )
                    }

                    position {
                        connect (
                            start to start of parent,
                            top to bottom of signedDate
                        )
                    }

                    description {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of position margin dip(margin)
                        )
                    }
                }

                player.playerFanart.let {
                    if (!it.isNullOrEmpty()) {
                        Picasso.get()
                            .load(it)
                            .placeholder(R.drawable.ic_placeholder_black_48dp)
                            .error(R.drawable.ic_error_black_48dp)
                            .into(fanart)
                    } else {
                        fanart.visibility = View.GONE
                    }
                }

                player.playerThumbnail.let {
                    if (!it.isNullOrEmpty()) {
                        Picasso.get()
                            .load(it)
                            .placeholder(R.drawable.ic_placeholder_black_48dp)
                            .error(R.drawable.ic_error_black_48dp)
                            .into(thumb)
                    } else {
                        thumb.visibility = View.GONE
                    }
                }

                description.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        }
    }
}