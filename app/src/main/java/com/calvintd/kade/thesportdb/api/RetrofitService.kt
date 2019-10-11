package com.calvintd.kade.thesportdb.api

import com.calvintd.kade.thesportdb.model.LeagueResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("/api/v1/json/1/search_all_leagues.php?s=Soccer")
    suspend fun getSoccerLeagues(): Response<LeagueResponse>
}