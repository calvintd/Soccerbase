package com.calvintd.kade.soccerbase.utils

import com.calvintd.kade.soccerbase.model.Match
import com.calvintd.kade.soccerbase.model.MatchResponse
import com.calvintd.kade.soccerbase.model.TeamResponse

object MatchDataProcessing {
    val splitter = MatchDetailsSplitter
    val formatter = MatchDateTimeFormatter

    fun matchDataInit (matchResponseItem: MatchResponse.Matches) : Match {
        // general data
        val matchId = matchResponseItem.matchId
        val homeTeamId = matchResponseItem.homeTeamId
        val awayTeamId = matchResponseItem.awayTeamId
        val homeName = matchResponseItem.homeName
        val awayName = matchResponseItem.awayName
        var homeBadge: String? = null
        var awayBadge: String? = null
        val homeScore = matchResponseItem.homeSoore
        val awayScore = matchResponseItem.awayScore

        // first = date; second = time
        val matchDateTime = formatter.format(matchResponseItem.matchDate, matchResponseItem.matchTime)
        val matchDate = matchDateTime.first
        val matchTime = matchDateTime.second

        // home
        val homeGoals = matchResponseItem.homeGoals
        val homeGoalDetails = splitter.split(homeGoals)

        val homeRedCards = matchResponseItem.homeRedCards
        val homeRedCardDetails = splitter.split(homeRedCards)

        val homeYellowCards = matchResponseItem.homeYellowCards
        val homeYellowCardDetails = splitter.split(homeYellowCards)

        val homeGoalkeeper = matchResponseItem.homeGoalkeeper
        val homeGoalkeeperDetails = splitter.split(homeGoalkeeper)

        val homeDefense = matchResponseItem.homeDefense
        val homeDefenseDetails = splitter.split(homeDefense)

        val homeMidfield = matchResponseItem.homeMidfield
        val homeMidfieldDetails = splitter.split(homeMidfield)

        val homeForward = matchResponseItem.homeForward
        val homeForwardDetails = splitter.split(homeForward)

        val homeSubstitutes = matchResponseItem.homeSubstitutes
        val homeSubstitutesDetails = splitter.split(homeSubstitutes)

        // away
        val awayGoals = matchResponseItem.awayGoals
        val awayGoalDetails = splitter.split(awayGoals)

        val awayRedCards = matchResponseItem.awayRedCards
        val awayRedCardDetails = splitter.split(awayRedCards)

        val awayYellowCards = matchResponseItem.awayYellowCards
        val awayYellowCardDetails = splitter.split(awayYellowCards)

        val awayGoalkeeper = matchResponseItem.awayGoalkeeper
        val awayGoalkeeperDetails = splitter.split(awayGoalkeeper)

        val awayDefense = matchResponseItem.awayDefense
        val awayDefenseDetails = splitter.split(awayDefense)

        val awayMidfield = matchResponseItem.awayMidfield
        val awayMidfieldDetails = splitter.split(awayMidfield)

        val awayForward = matchResponseItem.awayForward
        val awayForwardDetails = splitter.split(awayForward)

        val awaySubstitutes = matchResponseItem.awaySubstitutes
        val awaySubstitutesDetails = splitter.split(awaySubstitutes)

        val matchItem = Match(
            // general data
            matchId = matchId,
            homeTeamId = homeTeamId?.toInt(),
            awayTeamId = awayTeamId?.toInt(),
            homeName = homeName,
            awayName = awayName,
            homeBadge = null,
            awayBadge = null,
            homeScore = homeScore,
            awayScore = awayScore,
            matchDate = matchDate,
            matchTime = matchTime,

            // home
            homeGoalsCount = homeGoalDetails.size,
            homeGoalDetails = homeGoalDetails,
            homeRedCardsCount = homeRedCardDetails.size,
            homeRedCardDetails = homeRedCardDetails,
            homeYellowCardsCount = homeYellowCardDetails.size,
            homeYellowCardDetails = homeYellowCardDetails,
            homeGoalkeeper = homeGoalkeeperDetails,
            homeDefense = homeDefenseDetails,
            homeMidfield = homeMidfieldDetails,
            homeForward = homeForwardDetails,
            homeSubstitutes = homeSubstitutesDetails,

            // away
            awayGoalsCount = awayGoalDetails.size,
            awayGoalDetails = awayGoalDetails,
            awayRedCardsCount = awayRedCardDetails.size,
            awayRedCardDetails = awayRedCardDetails,
            awayYellowCardsCount = awayYellowCardDetails.size,
            awayYellowCardDetails = awayYellowCardDetails,
            awayGoalkeeper = awayGoalkeeperDetails,
            awayDefense = awayDefenseDetails,
            awayMidfield = awayMidfieldDetails,
            awayForward = awayForwardDetails,
            awaySubstitutes = awaySubstitutesDetails
        )

        return matchItem
    }

    fun assignBadge (matchItem: Match, homeTeamResponseItem: List<TeamResponse.Teams>, awayTeamResponseItem: List<TeamResponse.Teams>) : Match {
        val assignedMatchItem = matchItem

        assignedMatchItem.homeBadge = homeTeamResponseItem.last().teamBadge.plus("/preview")
        assignedMatchItem.awayBadge = awayTeamResponseItem.last().teamBadge.plus("/preview")

        return assignedMatchItem
    }
}