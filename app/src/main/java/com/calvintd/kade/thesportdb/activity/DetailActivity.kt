package com.calvintd.kade.thesportdb.activity

import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.thesportdb.R
import com.calvintd.kade.thesportdb.model.League
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.getParcelableExtra("league") as League
        val leagueName = bundle.name
        val leagueLogo = bundle.logo
        val leagueDescription = bundle.description

        supportActionBar?.title = resources.getText(R.string.detail_activity_title)

        scrollView {
            constraintLayout {
                lparams(width = matchParent, height = matchParent)

                val logo = imageView {
                    id = R.id.ivDetailLogo
                    image = resources.getDrawable(R.drawable.ic_placeholder_black_48dp, theme)
                }.lparams(width = wrapContent, height = wrapContent)

                val name = textView {
                    id = R.id.tvDetailName
                    text = leagueName
                    gravity = Gravity.CENTER
                    textSize = 28f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = matchConstraint, height = wrapContent)

                val description = textView {
                    id = R.id.tvDetailDescription
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

                    logo {
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
                            top to bottom of logo margin dip(margin)
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

                logo.let {
                    Picasso.get()
                        .load(leagueLogo)
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