package com.calvintd.kade.soccerbase.api

import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/api/v1/json/1/search_all_leagues.php?s=Soccer")
    suspend fun getSoccerLeagues(): Call<LeagueResponse>

    @GET("/api/v1/json/1/searchevents.php")
    suspend fun getMatchesSearch(@Query("e") name: String): Call<MatchResponse>

    @GET("/api/v1/json/1/eventspastleague.php")
    suspend fun getPastLeagueMatches(@Query("id") id: Int): Call<MatchLeagueResponse>

    @GET("/api/v1/json/1/eventsnextleague.php")
    suspend fun getUpcomingLeagueMatches(@Query("id") id: Int): Call<MatchLeagueResponse>

    @GET("/api/v1/json/1/lookupteam.php")
    suspend fun getTeamDetails(@Query("id") id: Int?): Response<TeamResponse>
}