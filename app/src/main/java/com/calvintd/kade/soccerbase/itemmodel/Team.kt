package com.calvintd.kade.soccerbase.itemmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team (
    var id: Int? = null,
    var teamId: Int? = null,
    var teamYearFormed: Int? = null,
    var teamStadium: String? = null,
    var teamStadiumLocation: String? = null,
    var teamStadiumCapacity: Int? = null,
    var teamWebsite: String? = null,
    var teamFacebook: String? = null,
    var teamTwitter: String? = null,
    var teamInstagram: String? = null,
    var teamYoutube: String? = null,
    var teamDescription: String? = null,
    var teamBadge: String? = null,
    var teamBanner: String? = null ) : Parcelable {

    companion object {
        const val ID = "ID_"

        // favorite teams
        const val TABLE_FAVORITE = "TABLE_FAVORITE_TEAMS"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_YEAR_FORMED = "TEAM_YEAR_FORMED"
        const val TEAM_STADIUM = "TEAM_STADIUM"
        const val TEAM_STADIUM_LOCATION = "TEAM_STADIUM_LOCATION"
        const val TEAM_STADIUM_CAPACITY = "TEAM_STADIUM_CAPACITY"
        const val TEAM_WEBSITE = "TEAM_WEBSITE"
        const val TEAM_FACEBOOK = "TEAM_FACEBOOK"
        const val TEAM_TWITTER = "TEAM_TWITTER"
        const val TEAM_INSTAGRAM = "TEAM_INSTAGRAM"
        const val TEAM_YOUTUBE = "TEAM_YOUTUBE"
        const val TEAM_BADGE = "TEAM_BADGE"
        const val TEAM_BANNER = "TEAM_BANNER"
    }

}