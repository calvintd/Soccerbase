package com.calvintd.kade.soccerbase.utils.matchutils

import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchResponseItem
import com.calvintd.kade.soccerbase.itemmodel.TeamResponseItem

object MatchDataProcessor {
    private val splitter = MatchDetailsSplitter
    private val formatter = MatchDateTimeFormatter

    fun matchDataInit (matchResponseItem: MatchResponseItem) : Match {
        // general data
        val matchId = matchResponseItem.matchId
        val homeTeamId = matchResponseItem.homeTeamId
        val awayTeamId = matchResponseItem.awayTeamId
        val homeName = matchResponseItem.homeName
        val awayName = matchResponseItem.awayName
        val homeScore = matchResponseItem.homeSoore
        val awayScore = matchResponseItem.awayScore

        // first = date; second = time
        val matchDateTime = MatchDateTimeFormatter.format(
            matchResponseItem.matchDate,
            matchResponseItem.matchTime
        )
        val matchDate = matchDateTime.first
        val matchTime = matchDateTime.second

        // home
        val homeGoals = matchResponseItem.homeGoals
        val homeGoalDetails =
            MatchDetailsSplitter.split(homeGoals)

        val homeRedCards = matchResponseItem.homeRedCards
        val homeRedCardDetails =
            MatchDetailsSplitter.split(homeRedCards)

        val homeYellowCards = matchResponseItem.homeYellowCards
        val homeYellowCardDetails =
            MatchDetailsSplitter.split(homeYellowCards)

        val homeGoalkeeper = matchResponseItem.homeGoalkeeper
        val homeGoalkeeperDetails =
            MatchDetailsSplitter.split(homeGoalkeeper)

        val homeDefense = matchResponseItem.homeDefense
        val homeDefenseDetails =
            MatchDetailsSplitter.split(homeDefense)

        val homeMidfield = matchResponseItem.homeMidfield
        val homeMidfieldDetails =
            MatchDetailsSplitter.split(homeMidfield)

        val homeForward = matchResponseItem.homeForward
        val homeForwardDetails =
            MatchDetailsSplitter.split(homeForward)

        val homeSubstitutes = matchResponseItem.homeSubstitutes
        val homeSubstitutesDetails =
            MatchDetailsSplitter.split(homeSubstitutes)

        // away
        val awayGoals = matchResponseItem.awayGoals
        val awayGoalDetails =
            MatchDetailsSplitter.split(awayGoals)

        val awayRedCards = matchResponseItem.awayRedCards
        val awayRedCardDetails =
            MatchDetailsSplitter.split(awayRedCards)

        val awayYellowCards = matchResponseItem.awayYellowCards
        val awayYellowCardDetails =
            MatchDetailsSplitter.split(awayYellowCards)

        val awayGoalkeeper = matchResponseItem.awayGoalkeeper
        val awayGoalkeeperDetails =
            MatchDetailsSplitter.split(awayGoalkeeper)

        val awayDefense = matchResponseItem.awayDefense
        val awayDefenseDetails =
            MatchDetailsSplitter.split(awayDefense)

        val awayMidfield = matchResponseItem.awayMidfield
        val awayMidfieldDetails =
            MatchDetailsSplitter.split(awayMidfield)

        val awayForward = matchResponseItem.awayForward
        val awayForwardDetails =
            MatchDetailsSplitter.split(awayForward)

        val awaySubstitutes = matchResponseItem.awaySubstitutes
        val awaySubstitutesDetails =
            MatchDetailsSplitter.split(awaySubstitutes)

        return Match(
            // general data
            matchId = matchId,
            homeTeamId = homeTeamId?.toInt(),
            awayTeamId = awayTeamId?.toInt(),
            homeName = homeName,
            awayName = awayName,
            homeScore = homeScore,
            awayScore = awayScore,
            matchDate = matchDate,
            matchTime = matchTime,

            // home
            homeGoalDetails = homeGoalDetails,
            homeRedCardDetails = homeRedCardDetails,
            homeYellowCardDetails = homeYellowCardDetails,
            homeGoalkeeper = homeGoalkeeperDetails,
            homeDefense = homeDefenseDetails,
            homeMidfield = homeMidfieldDetails,
            homeForward = homeForwardDetails,
            homeSubstitutes = homeSubstitutesDetails,

            // away
            awayGoalDetails = awayGoalDetails,
            awayRedCardDetails = awayRedCardDetails,
            awayYellowCardDetails = awayYellowCardDetails,
            awayGoalkeeper = awayGoalkeeperDetails,
            awayDefense = awayDefenseDetails,
            awayMidfield = awayMidfieldDetails,
            awayForward = awayForwardDetails,
            awaySubstitutes = awaySubstitutesDetails
        )
    }

    fun assignBadge (matchItem: Match, homeTeamResponseItem: List<TeamResponseItem>?, awayTeamResponseItem: List<TeamResponseItem>?) : Match {
        if (homeTeamResponseItem != null && awayTeamResponseItem != null) {
            matchItem.homeBadge = homeTeamResponseItem.last().teamBadge.plus("/preview")
            matchItem.awayBadge = awayTeamResponseItem.last().teamBadge.plus("/preview")
        }

        return matchItem
    }
}