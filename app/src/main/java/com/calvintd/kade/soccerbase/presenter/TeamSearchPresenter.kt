package com.calvintd.kade.soccerbase.presenter

import com.calvintd.kade.soccerbase.itemmodel.Team
import com.calvintd.kade.soccerbase.itemmodel.TeamResponse
import com.calvintd.kade.soccerbase.repository.TeamResponseRepository
import com.calvintd.kade.soccerbase.repository.callback.TeamResponseRepositoryCallback
import com.calvintd.kade.soccerbase.utils.fetchers.FetchTeamsCoroutines
import com.calvintd.kade.soccerbase.utils.test.CoroutineContextProvider
import com.calvintd.kade.soccerbase.view.TeamSearchView
import kotlinx.coroutines.*
import retrofit2.Response

class TeamSearchPresenter(private val view: TeamSearchView, private val repository: TeamResponseRepository,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun loadTeamsByQuery(query: String) {
        val fetchedTeams = CoroutineScope(Dispatchers.IO).async {
            getFetchedTeams(query)
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (fetchedTeams.await().isEmpty()) {
                view.showNoResultsFound(query)
            } else {
                view.loadTeamsByQuery(fetchedTeams.await(), query)
            }
        }
    }

    suspend fun getFetchedTeams(query: String): List<Team> {
        return withContext(context.main) {
            var response: TeamResponse? = TeamResponse(listOf())

            repository.getTeamsByName(query, object : TeamResponseRepositoryCallback<TeamResponse> {
                override fun onTeamDataLoaded(data: TeamResponse?) {
                    response = data
                    view.onTeamDataLoaded(data)
                }

                override fun onTeamDataError(response: Response<TeamResponse>) {
                    view.onTeamDataError(response)
                }

            })

            FetchTeamsCoroutines.getFetchedTeams(response)
        }
    }
}