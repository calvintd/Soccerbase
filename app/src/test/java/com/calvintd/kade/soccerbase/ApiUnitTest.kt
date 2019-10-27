package com.calvintd.kade.soccerbase

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.api.RetrofitService
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.presenter.LeagueSchedulePastMatchesPresenter
import com.calvintd.kade.soccerbase.presenter.LeagueScheduleUpcomingMatchesPresenter
import com.calvintd.kade.soccerbase.presenter.MatchSearchPresenter
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiUnitTest {
    private val leagueId = 4328
    private val leagueName = "English Premier League"

    private lateinit var service: RetrofitService

    private lateinit var leagueSchedulePastMatchesPresenter: LeagueSchedulePastMatchesPresenter
    private lateinit var leagueScheduleUpcomingMatchesPresenter: LeagueScheduleUpcomingMatchesPresenter
    private lateinit var matchSearchPresenter: MatchSearchPresenter

    private lateinit var leagueScheduleView: LeagueScheduleView
    private lateinit var matchSearchView: MatchSearchView

    @Mock
    private lateinit var matchResponse: Response<MatchResponse>
    @Mock
    private lateinit var matchLeagueResponse: Response<MatchLeagueResponse>

    @Before
    fun setUp() {
        service = mock(RetrofitService::class.java)

        leagueSchedulePastMatchesPresenter = mock(LeagueSchedulePastMatchesPresenter::class.java)
        leagueScheduleUpcomingMatchesPresenter = mock(LeagueScheduleUpcomingMatchesPresenter::class.java)
        matchSearchPresenter = mock(MatchSearchPresenter::class.java)

        leagueScheduleView = mock(LeagueScheduleView::class.java)
        matchSearchView = mock(MatchSearchView::class.java)

        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadLeagueSchedulePastMatchesTest() {
        val leaguesList = mutableListOf<Match>()

        runBlocking {
            `when`(service.getPastLeagueMatches(leagueId)).thenReturn(matchLeagueResponse)
            `when`(leagueSchedulePastMatchesPresenter.getFetchedMatches(leagueId)).thenReturn(leaguesList)

            leagueSchedulePastMatchesPresenter.loadMatchesByLeague(leagueId, leagueName)

            verify(leagueScheduleView).loadMatchesByLeague(leaguesList, leagueName)
            verify(leagueScheduleView).showNoResultsFound(leagueName)
            verify(leagueScheduleView).showResponseError(
                matchLeagueResponse.code(),
                matchLeagueResponse.errorBody()
            )
            verify(leagueScheduleView).showException(HttpException(matchLeagueResponse))
        }
    }

    @Test
    fun loadLeagueScheduleUpcomingMatchesTest() {
        runBlocking {

        }
    }

    @Test
    fun loadMatchSearchTest() {
        runBlocking {

        }
    }

    @Test
    fun loadMatchDetailsTest() {

    }

}
