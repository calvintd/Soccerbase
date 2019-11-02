package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.League
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepository
import com.calvintd.kade.soccerbase.repository.TeamResponseRepository
import com.calvintd.kade.soccerbase.repository.callback.LeagueResponseRepositoryCallback
import com.calvintd.kade.soccerbase.repository.callback.TeamResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.fetchers.FetchLeaguesCoroutines
import com.calvintd.kade.soccerbase.utils.fetchers.FetchTeamsCoroutines
import com.calvintd.kade.soccerbase.utils.test.CoroutineContextProvider
import com.calvintd.kade.soccerbase.view.TeamListingView
import kotlinx.coroutines.*
import retrofit2.Response

class TeamListingPresenter(private val view: TeamListingView, private val leagueRepository: LeagueResponseRepository,
                           private val teamRepository: TeamResponseRepository,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun loadDataIntoSpinner() {
        val fetchedLeagues = CoroutineScope(Dispatchers.IO).async {
            getFetchedLeagues()
        }

        CoroutineScope(Dispatchers.IO).launch {
            view.loadDataIntoSpinner(fetchedLeagues.await())
        }
    }

    fun loadLeagueTeams(leagueId: Int) {

    }

    suspend fun getFetchedLeagues(): List<League> {
        return withContext(context.main) {
            var response: LeagueResponse? = LeagueResponse(listOf())

            leagueRepository.getSoccerLeagues(object:
                LeagueResponseRepositoryCallback<LeagueResponse> {
                override fun onLeagueDataLoaded(data: LeagueResponse?) {
                    response = data
                    view.onLeagueDataLoaded(data)
                }

                override fun onLeagueDataError(response: Response<LeagueResponse>) {
                    view.onLeagueDataError(response)
                }
            })

            FetchLeaguesCoroutines.getFetchedLeagues(response)
        }
    }

    suspend fun getFetchedTeams(): List<Team> {
        return withContext(context.main) {
            var response: TeamResponse? = TeamResponse(listOf())

            teamRepository.getTeamsByLeague(object:
                TeamResponseRepositoryCallback<TeamResponse> {
                override fun onTeamDataLoaded(data: TeamResponse?) {
                    response = data
                    view.onTeamDataLoaded(data)
                }

                override fun onTeamDataError(response: Response<TeamResponse>) {
                    view.onTeamDataError(response)
                }

            })

            FetchTeamsCoroutines
        }
    }
}