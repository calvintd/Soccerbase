package com.calvintd.kade.soccerbase.utils.fetchers

import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object FetchTeamsCoroutines {
    suspend fun getFetchedTeams(data: TeamResponse?): List<Team> {
        return withContext(Dispatchers.Main) {
            val fetchedTeams = mutableListOf<Team>()
            val teamResponseItems = data!!.teams

            for (i in teamResponseItems.indices) {
                val item = teamResponseItems[i]

                val teamId = item.teamId
                val teamName = item.teamName
                val teamYearFormed = item.teamYearFormed
                val teamStadium = item.teamStadium
                val teamStadiumLocation = item.teamStadiumLocation
                val teamStadiumCapacity = item.teamStadiumCapacity
                val teamWebsite = item.teamWebsite
                val teamFacebook = item.teamFacebook
                val teamTwitter = item.teamTwitter
                val teamInstagram = item.teamInstagram
                val teamYoutube = item.teamYoutube
                val teamDescription = item.teamDescription
                val teamBadge = item.teamBadge.plus("/preview")
                val teamBanner = item.teamBanner.plus("/preview")

                val team = Team(
                    teamId = teamId,
                    teamName = teamName,
                    teamYearFormed = teamYearFormed,
                    teamStadium = teamStadium,
                    teamStadiumCapacity = teamStadiumCapacity,
                    teamStadiumLocation = teamStadiumLocation,
                    teamWebsite = teamWebsite,
                    teamFacebook = teamFacebook,
                    teamTwitter = teamTwitter,
                    teamInstagram = teamInstagram,
                    teamYoutube = teamYoutube,
                    teamDescription = teamDescription,
                    teamBadge = teamBadge,
                    teamBanner = teamBanner
                )

                fetchedTeams.add(team)
            }
            fetchedTeams
        }
    }
}