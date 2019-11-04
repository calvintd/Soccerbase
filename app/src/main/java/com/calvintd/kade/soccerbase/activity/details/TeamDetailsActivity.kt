package com.calvintd.kade.soccerbase.activity.details

import android.graphics.Typeface
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class TeamDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.team_details_activity_title)

        val team = intent.getParcelableExtra("team") as Team

        scrollView {
            constraintLayout {
                lparams(width = matchParent, height = matchParent)
                padding = 16

                val banner = imageView {
                    id = R.id.ivTeamDetailsActivityBanner
                }.lparams(width = wrapContent, height = wrapContent)

                val badge = imageView {
                    id = R.id.ivTeamDetailsActivityBadge
                }.lparams(width = wrapContent, height = wrapContent)

                val name = textView {
                    id = R.id.tvTeamDetailsActivityName
                    text = team.teamName
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = matchConstraint, height = wrapContent)

                val yearFormed = textView {
                    id = R.id.tvTeamDetailsActivityYearFormed
                    text = String.format(resources.getString(R.string.team_details_year_formed), team.teamYearFormed)
                }.lparams(width = wrapContent, height = wrapContent)

                val stadium = textView {
                    id = R.id.tvTeamDetailsActivityStadium
                    text = String.format(resources.getString(R.string.team_details_stadium), team.teamStadium)
                }.lparams(width = wrapContent, height = wrapContent)

                val stadiumLocation = textView {
                    id = R.id.tvTeamDetailsActivityStadiumLocation
                    text = String.format(resources.getString(R.string.team_details_stadium_location), team.teamStadiumLocation)
                }.lparams(width = wrapContent, height = wrapContent)

                val stadiumCapacity = textView {
                    id = R.id.tvTeamDetailsActivityStadiumCapacity
                    text = String.format(resources.getString(R.string.team_details_stadium_capacity), team.teamStadiumCapacity)
                }.lparams(width = wrapContent, height = wrapContent)

                val website = textView {
                    id = R.id.tvTeamDetailsActivityWebsite
                    text = String.format(resources.getString(R.string.team_details_website), team.teamWebsite)
                }.lparams(width = wrapContent, height = wrapContent)

                val facebook = textView {
                    id = R.id.tvTeamDetailsActivityFacebook
                    text = String.format(resources.getString(R.string.team_details_facebook), team.teamFacebook)
                }.lparams(width = wrapContent, height = wrapContent)

                val twitter = textView {
                    id = R.id.tvTeamDetailsActivityTwitter
                    text = String.format(resources.getString(R.string.team_details_twitter), team.teamTwitter)
                }.lparams(width = wrapContent, height = wrapContent)

                val instagram = textView {
                    id = R.id.tvTeamDetailsActivityInstagram
                    text = String.format(resources.getString(R.string.team_details_instagram), team.teamInstagram)
                }.lparams(width = wrapContent, height = wrapContent)

                val youtube = textView {
                    id = R.id.tvTeamDetailsActivityYoutube
                    text = String.format(resources.getString(R.string.team_details_youtube), team.teamYoutube)
                }.lparams(width = wrapContent, height = wrapContent)

                val description = textView {
                    id = R.id.tvTeamDetailsActivityDescription
                    text = team.teamDescription
                }.lparams(width = matchConstraint, height = wrapContent)

                applyConstraintSet {
                    val parent = ConstraintSet.PARENT_ID
                    val start = ConstraintSetBuilder.Side.START
                    val end = ConstraintSetBuilder.Side.END
                    val top = ConstraintSetBuilder.Side.TOP
                    val bottom = ConstraintSetBuilder.Side.BOTTOM
                    val margin = 16
                    val halfMargin = margin/2

                    banner {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to top of parent
                        )
                    }

                    badge {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of banner margin dip(margin)
                        )
                    }

                    name {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of badge margin dip(halfMargin)
                        )
                    }

                    yearFormed {
                        connect (
                            start to start of parent,
                            top to bottom of name margin dip(margin)
                        )
                    }

                    stadium {
                        connect (
                            start to start of parent,
                            top to bottom of yearFormed
                        )
                    }

                    stadiumLocation {
                        connect (
                            start to start of parent,
                            top to bottom of stadium
                        )
                    }

                    stadiumCapacity {
                        connect (
                            start to start of parent,
                            top to bottom of stadiumLocation
                        )
                    }

                    website {
                        connect (
                            start to start of parent,
                            top to bottom of stadiumCapacity
                        )
                    }

                    facebook {
                        connect (
                            start to start of parent,
                            top to bottom of website
                        )
                    }

                    twitter {
                        connect (
                            start to start of parent,
                            top to bottom of facebook
                        )
                    }

                    instagram {
                        connect (
                            start to start of parent,
                            top to bottom of twitter
                        )
                    }

                    youtube {
                        connect (
                            start to start of parent,
                            top to bottom of instagram
                        )
                    }

                    description {
                        connect (
                            start to start of parent,
                            top to bottom of youtube margin dip(margin)
                        )
                    }
                }

                team.teamBanner.let {
                    Picasso.get()
                        .load(it)
                        .fit()
                        .centerCrop()
                        .placeholder(R.drawable.ic_placeholder_black_48dp)
                        .error(R.drawable.ic_error_black_48dp)
                        .into(banner)
                }

                team.teamBadge.let {
                    Picasso.get()
                        .load(it)
                        .fit()
                        .centerCrop()
                        .placeholder(R.drawable.ic_placeholder_black_48dp)
                        .error(R.drawable.ic_error_black_48dp)
                        .into(badge)
                }
            }
        }
    }
}