package com.calvintd.kade.soccerbase.activity.details

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.database.database
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.presenter.TeamDetailsPresenter
import com.calvintd.kade.soccerbase.view.ItemDetailsView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamDetailsActivity : AppCompatActivity(), ItemDetailsView {
    private lateinit var favoriteIcon: ImageView
    private lateinit var favoriteName: TextView
    private lateinit var alertTitle: String
    private lateinit var alertMessage: String
    private var isFavorited: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.team_details_activity_title)

        val team = intent.getParcelableExtra("team") as Team
        val teamId = team.teamId!!
        val favoriteIconSize = 96

        val presenter = TeamDetailsPresenter(this)

        isFavorited = presenter.returnFavorite(teamId, database)

        scrollView {
            constraintLayout {
                lparams(width = matchParent, height = matchParent)
                padding = 16

                val favorite = linearLayout {
                    id = R.id.llTeamDetailsActivityFavoriteLayout
                    lparams(width = wrapContent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_VERTICAL
                    rightPadding = 16
                    topPadding = 16

                    favoriteIcon = imageView {
                        id = R.id.ivTeamDetailsActivityFavoriteIcon
                        image = resources.getDrawable(R.drawable.ic_not_favorite_black_48dp, context.theme)
                        padding = 8
                    }.lparams(width = favoriteIconSize, height = favoriteIconSize)

                    favoriteName = textView {
                        id = R.id.tvTeamDetailsActivityFavoriteName
                        text = resources.getString(R.string.favorite_matches_favorite_match)
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(width = wrapContent, height = wrapContent)

                    presenter.checkFavorite(isFavorited)

                    onClick {
                        alert(alertMessage, alertTitle) {
                            yesButton {
                                if(!isFavorited) {
                                    presenter.addToFavorites(team, database)
                                } else {
                                    presenter.removeFromFavorites(teamId, database)
                                }
                            }
                            noButton {

                            }
                        }.show()
                    }
                }

                val banner = imageView {
                    id = R.id.ivTeamDetailsActivityBanner
                }.lparams(width = matchConstraint, height = wrapContent)

                val badge = imageView {
                    id = R.id.ivTeamDetailsActivityBadge
                }.lparams(width = wrapContent, height = wrapContent)

                val name = textView {
                    id = R.id.tvTeamDetailsActivityName
                    text = team.teamName
                    textSize = 20f
                    gravity = Gravity.CENTER
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = matchConstraint, height = wrapContent)

                val yearFormed = textView {
                    id = R.id.tvTeamDetailsActivityYearFormed
                    text = String.format(resources.getString(R.string.team_details_year_formed), team.teamYearFormed.toString())
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
                    text = String.format(resources.getString(R.string.team_details_stadium_capacity), team.teamStadiumCapacity.toString())
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
                    val doubleMargin = margin * 2
                    val halfMargin = margin / 2

                    favorite {
                        connect (
                            end to end of parent,
                            top to top of parent
                        )
                    }

                    banner {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of favorite margin dip(halfMargin)
                        )
                    }

                    badge {
                        connect (
                            start to start of parent,
                            end to end of parent,
                            top to bottom of banner margin dip(doubleMargin)
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
                    if (!it.isNullOrEmpty()) {
                        Picasso.get()
                            .load(it)
                            .placeholder(R.drawable.ic_placeholder_black_48dp)
                            .error(R.drawable.ic_error_black_48dp)
                            .into(banner)
                    } else {
                        banner.visibility = View.GONE
                    }
                }

                team.teamBadge.let {
                    if (!it.isNullOrEmpty()) {
                        Picasso.get()
                            .load(it)
                            .placeholder(R.drawable.ic_placeholder_black_48dp)
                            .error(R.drawable.ic_error_black_48dp)
                            .into(badge)
                    } else {
                        badge.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun checkedFavorite(isFavorited: Boolean) {
        changeFavoriteUI(isFavorited)
    }

    override fun addedToFavorites() {
        changeFavoriteUI(true)
        toast(resources.getString(R.string.favorite_matches_added_to_favorites))
    }

    override fun removedFromFavorites() {
        changeFavoriteUI(false)
        toast(resources.getString(R.string.favorite_matches_removed_from_favorites))
    }

    override fun showError(message: String?) {
        toast(String.format(resources.getString(R.string.error_messages_sqlite_exception), message))
    }

    private fun changeFavoriteUI(isFavorited: Boolean) {
        if (!isFavorited) {
            favoriteIcon.image = resources.getDrawable(R.drawable.ic_not_favorite_black_48dp, theme)
            favoriteName.text = resources.getString(R.string.favorite_teams_favorite_team)
            alertTitle = resources.getString(R.string.favorite_teams_add_confirmation_title)
            alertMessage = resources.getString(R.string.favorite_teams_add_confirmation_message)
        }
        else {
            favoriteIcon.image = resources.getDrawable(R.drawable.ic_favorited_black_48dp, theme)
            favoriteName.text = resources.getString(R.string.favorite_teams_unfavorite_team)
            alertTitle = resources.getString(R.string.favorite_teams_remove_confirmation_title)
            alertMessage = resources.getString(R.string.favorite_teams_remove_confirmation_message)
        }
        this.isFavorited = isFavorited
    }
}