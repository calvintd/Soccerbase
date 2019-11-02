package com.calvintd.kade.soccerbase.itemmodel

import com.google.gson.annotations.SerializedName

class TeamResponseItem (
    @SerializedName("idTeam")
    var teamId: Int?,
    @SerializedName("strTeam")
    var teamName: String?,
    @SerializedName("intFormedYear")
    var teamYearFormed: Int?,
    @SerializedName("strStadium")
    var teamStadium: String?,
    @SerializedName("strStadiumLocation")
    var teamStadiumLocation: String?,
    @SerializedName("intStadiumCapacity")
    var teamStadiumCapacity: Int?,
    @SerializedName("strWebsite")
    var teamWebsite: String?,
    @SerializedName("strFacebook")
    var teamFacebook: String?,
    @SerializedName("strTwitter")
    var teamTwitter: String?,
    @SerializedName("strInstagram")
    var teamInstagram: String?,
    @SerializedName("strYoutube")
    var teamYoutube: String?,
    @SerializedName("strDescriptionEN")
    var teamDescription: String?,
    @SerializedName("strTeamBadge")
    var teamBadge: String?,
    @SerializedName("strTeamBanner")
    var teamBanner: String?
)