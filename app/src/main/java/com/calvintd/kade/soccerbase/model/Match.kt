package com.calvintd.kade.soccerbase.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Match (
    // general data
    var matchId: Int?, var homeTeamId: Int?, var awayTeamId: Int?, var homeName: String?, var awayName: String?, var homeBadge: String?,
    var awayBadge: String?, var homeScore: Int?, var awayScore: Int?, var matchDate: String?, var matchTime: String?,
    
    // home
    var homeGoalsCount: Int?, var homeGoalDetails: List<String?>, var homeRedCardsCount: Int?, var homeRedCardDetails: List<String?>,
    var homeYellowCardsCount: Int?, var homeYellowCardDetails: List<String?>, var homeGoalkeeper: List<String?>, var homeDefense: List<String?>,
    var homeMidfield: List<String?>, var homeForward: List<String?>, var homeSubstitutes: List<String?>,
    
    // away
    var awayGoalsCount: Int?, var awayGoalDetails: List<String?>, var awayRedCardsCount: Int?, var awayRedCardDetails: List<String?>,
    var awayYellowCardsCount: Int?, var awayYellowCardDetails: List<String?>, var awayGoalkeeper: List<String?>, var awayDefense: List<String?>,
    var awayMidfield: List<String?>, var awayForward: List<String?>, var awaySubstitutes: List<String?>): Parcelable