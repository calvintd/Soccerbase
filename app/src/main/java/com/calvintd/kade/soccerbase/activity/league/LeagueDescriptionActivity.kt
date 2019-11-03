package com.calvintd.kade.soccerbase.activity.league

import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.League
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*

class LeagueDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getText(R.string.league_description_activity_title)

        val league = intent.getParcelableExtra("league") as League
        val leagueName = league.name
        val leagueBadge = league.badge
        val leagueDescription = league.description

        scrollView {
            constraintLayout {
                lparams(width = matchParent, height = matchParent)

                val badge = imageView {
                    id = R.id.ivDescriptionBadge
                    image = resources.getDrawable(R.drawable.ic_placeholder_black_48dp, theme)
                }.lparams(width = wrapContent, height = wrapContent)

                val name = textView {
                    id = R.id.tvDescriptionName
                    text = leagueName
                    gravity = Gravity.CENTER
                    textSize = 28f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = matchConstraint, height = wrapContent)

                val description = textView {
                    id = R.id.tvDescriptionDesc
                    text = leagueDescription
                    gravity = Gravity.START
                    textSize = 16f
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
                            end to end of parent margin dip(margin),
                            top to top of parent margin dip(margin)
                        )
                    }

                    name {
                        connect (
                            start to start of parent margin dip(margin),
                            end to end of parent margin dip(margin),
                            top to bottom of badge margin dip(margin)
                        )
                    }

                    description {
                        connect (
                            start to start of parent margin dip(margin),
                            end to end of parent margin dip(margin),
                            top to bottom of name margin dip(margin),
                            bottom to bottom of parent margin dip(margin)
                        )
                    }
                }

                badge.let {
                    Picasso.get()
                        .load(leagueBadge)
                        .fit()
                        .centerCrop()
                        .placeholder(R.drawable.ic_placeholder_black_48dp)
                        .error(R.drawable.ic_error_black_48dp)
                        .into(it)
                }

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    description.justificationMode = JUSTIFICATION_MODE_INTER_WORD
                }
            }
        }
    }
}