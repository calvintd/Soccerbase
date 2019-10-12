package com.calvintd.kade.soccerbase.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Match (
    // general data
    var matchId: Int?, var homeTeamId: Int?, var awayTeamId: Int?, var homeName: String?, var awayName: String?, var homeBadge: String?,
    var awayBadge: String?, var homeScore: Int?, var awayScore: Int?, var matchDate: String?, var matchTime: String?,
    
    // home
    var homeGoals: Int?, var homeGoalDetails: List<String?>, var homeRedCards: Int?, var homeRedCardDetails: List<String?>,
    var homeYellowCards: Int?, var homeYellowCardDetails: List<String?>, var homeGoalkeeper: String?, var homeDefense: List<String?>, 
    var homeMidfield: List<String?>, var homeForward: List<String?>, var homeSubstitutes: List<String?>,
    
    // away
    var awayGoals: Int?, var awayGoalDetails: List<String?>, var awayRedCards: Int?, var awayRedCardDetails: List<String?>,
    var awayYellowCards: Int?, var awayYellowCardDetails: List<String?>, var awayGoalkeeper: String?, var awayDefense: List<String?>,
    var awayMidfield: List<String?>, var awayForward: List<String?>, var awaySubstitutes: List<String?>): Parcelable