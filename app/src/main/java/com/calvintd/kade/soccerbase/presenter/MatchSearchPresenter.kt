package com.calvintd.kade.soccerbase.presenter

import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.adapter.MatchAdapter
import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.model.Match
import com.calvintd.kade.soccerbase.utils.MatchDateTimeFormatter
import com.calvintd.kade.soccerbase.utils.MatchDetailsSplitter
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MatchSearchPresenter(private val view: MatchSearchView) {
    fun loadMatchByQuery(recyclerView: RecyclerView?, query: String) {
        val matches = ArrayList<Match>()
        val instance = RetrofitInstance.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            val response = instance.getMatchesSearch(query)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val matchResponse = response.body()
                        if (matchResponse!!.matches.isEmpty()) {
                            view.showNoResultsFound(query)
                        } else {
                            val matchResponseItems = matchResponse.matches
                            for (i in matchResponseItems.indices) {
                                if (matchResponseItems[i].sport.equals("Soccer")) {
                                    val match = matchResponseItems[i]
                                    val splitter = MatchDetailsSplitter
                                    val formatter = MatchDateTimeFormatter

                                    // general data
                                    val matchId = match.matchId
                                    val homeTeamId = match.homeTeamId
                                    val awayTeamId = match.awayTeamId
                                    val homeName = match.homeName
                                    val awayName = match.awayName
                                    var homeBadge: String? = null
                                    var awayBadge: String? = null
                                    val homeScore = match.homeSoore
                                    val awayScore = match.awayScore

                                    // first = date; second = time
                                    val matchDateTime = formatter.format(match.matchDate, match.matchTime)
                                    val matchDate = matchDateTime.first
                                    val matchTime = matchDateTime.second

                                    // home
                                    val homeGoals = match.homeGoals
                                    val homeGoalDetails = splitter.split(homeGoals)

                                    val homeRedCards = match.homeRedCards
                                    val homeRedCardDetails = splitter.split(homeRedCards)

                                    val homeYellowCards = match.homeYellowCards
                                    val homeYellowCardDetails = splitter.split(homeYellowCards)

                                    val homeGoalkeeper = match.homeGoalkeeper
                                    val homeGoalkeeperDetails = splitter.split(homeGoalkeeper)

                                    val homeDefense = match.homeDefense
                                    val homeDefenseDetails = splitter.split(homeDefense)

                                    val homeMidfield = match.homeMidfield
                                    val homeMidfieldDetails = splitter.split(homeMidfield)

                                    val homeForward = match.homeForward
                                    val homeForwardDetails = splitter.split(homeForward)

                                    val homeSubstitutes = match.homeSubstitutes
                                    val homeSubstitutesDetails = splitter.split(homeSubstitutes)


                                    // away
                                    val awayGoals = match.awayGoals
                                    val awayGoalDetails = splitter.split(awayGoals)

                                    val awayRedCards = match.awayRedCards
                                    val awayRedCardDetails = splitter.split(awayRedCards)

                                    val awayYellowCards = match.awayYellowCards
                                    val awayYellowCardDetails = splitter.split(awayYellowCards)

                                    val awayGoalkeeper = match.awayGoalkeeper
                                    val awayGoalkeeperDetails = splitter.split(awayGoalkeeper)

                                    val awayDefense = match.awayDefense
                                    val awayDefenseDetails = splitter.split(awayDefense)

                                    val awayMidfield = match.awayMidfield
                                    val awayMidfieldDetails = splitter.split(awayMidfield)

                                    val awayForward = match.awayForward
                                    val awayForwardDetails = splitter.split(awayForward)

                                    val awaySubstitutes = match.awaySubstitutes
                                    val awaySubstitutesDetails = splitter.split(awaySubstitutes)

                                    // fetch team data
                                    CoroutineScope(Dispatchers.IO).launch {
                                        val hResponse = instance.getTeamDetails(homeTeamId!!.toInt())
                                        val aResponse = instance.getTeamDetails(awayTeamId!!.toInt())
                                        withContext(Dispatchers.Main) {
                                            try {
                                                if (response.isSuccessful) {
                                                    val homeResponse = hResponse.body()
                                                    val awayResponse = aResponse.body()

                                                    val homeResponseItems = homeResponse!!.teams
                                                    val awayResponseItems = awayResponse!!.teams

                                                    homeBadge = homeResponseItems.last().teamBadge.plus("/preview")
                                                    awayBadge = awayResponseItems.last().teamBadge.plus("/preview")

                                                    val matchItem = Match(
                                                        // general data
                                                        matchId = matchId,
                                                        homeTeamId = homeTeamId.toInt(),
                                                        awayTeamId = awayTeamId.toInt(),
                                                        homeName = homeName,
                                                        awayName = awayName,
                                                        homeBadge = homeBadge,
                                                        awayBadge = awayBadge,
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

                                                    matches.add(matchItem)

                                                    if(i == matchResponseItems.lastIndex) {
                                                        recyclerView?.adapter = MatchAdapter(matches)
                                                        view.loadMatchByQuery(query)
                                                    }
                                                } else {
                                                    view.showResponseError(hResponse.code(), hResponse.errorBody())
                                                }
                                            } catch (e: HttpException) {
                                                view.showException(e)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        view.showResponseError(response.code(), response.errorBody())
                    }
                } catch (e: HttpException) {
                    view.showException(e)
                }
            }
        }
    }
}