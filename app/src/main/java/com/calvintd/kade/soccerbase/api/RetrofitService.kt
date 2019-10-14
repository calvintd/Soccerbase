package com.calvintd.kade.soccerbase.api

import com.calvintd.kade.soccerbase.model.LeagueResponse
import com.calvintd.kade.soccerbase.model.MatchResponse
import com.calvintd.kade.soccerbase.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/api/v1/json/1/search_all_leagues.php?s=Soccer")
    suspend fun getSoccerLeagues(): Response<LeagueResponse>

    @GET("/api/v1/json/1/searchevents.php")
    suspend fun getMatchesSearch(@Query("e") name: String): Response<MatchResponse>

    @GET("/api/v1/json/1/eventspastleague.php")
    suspend fun getPastLeagueMatches(@Query("id") id: Int): Response<MatchResponse>

    @GET("/api/v1/json/1/eventsnextleague.php")
    suspend fun getUpcomingLeagueMatches(@Query("id") id: Int): Response<MatchResponse>

    @GET("/api/v1/json/1/lookupteam.php")
    suspend fun getTeamDetails(@Query("id") id: Int): Response<TeamResponse>
}