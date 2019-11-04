package com.calvintd.kade.soccerbase.api

import com.calvintd.kade.soccerbase.itemmodel.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/api/v1/json/1/search_all_leagues.php?s=Soccer")
    suspend fun getSoccerLeagues(): Response<LeagueResponse>

    @GET("/api/v1/json/1/searchevents.php")
    suspend fun getMatchesByTeamName(@Query("e") name: String): Response<MatchResponse>

    @GET("/api/v1/json/1/eventspastleague.php")
    suspend fun getPastLeagueMatches(@Query("id") leagueId: Int): Response<MatchLeagueResponse>

    @GET("/api/v1/json/1/eventsnextleague.php")
    suspend fun getUpcomingLeagueMatches(@Query("id") leagueId: Int): Response<MatchLeagueResponse>

    @GET("/api/v1/json/1/lookupteam.php")
    suspend fun getTeamDetails(@Query("id") teamId: Int?): Response<TeamResponse>

    @GET("/api/v1/json/1/lookup_all_teams.php")
    suspend fun getTeamsByLeague(@Query("id") leagueId: Int?): Response<TeamResponse>

    @GET("/api/v1/json/1/lookup_all_players.php")
    suspend fun getTeamPlayers(@Query("id") teamId: Int?): Response<PlayerResponse>

    @GET("/api/v1/json/1/lookuptable.php?s=1920")
    suspend fun getLeagueStandings(@Query("l") leagueId: Int?): Response<StandingsResponse>
}