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
import org.mockito.*
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiUnitTest {
    private val leagueId = 4328
    private val leagueName = "English Premier League"

    @Mock
    private var service: RetrofitService = RetrofitInstance.getInstance()

    private lateinit var leagueSchedulePastMatchesPresenter: LeagueSchedulePastMatchesPresenter
    private lateinit var leagueScheduleUpcomingMatchesPresenter: LeagueScheduleUpcomingMatchesPresenter
    private lateinit var matchSearchPresenter: MatchSearchPresenter

    private val leagueScheduleView: LeagueScheduleView = mock(LeagueScheduleView::class.java)
    private val matchSearchView: MatchSearchView = mock(MatchSearchView::class.java)

    @Mock
    private lateinit var matchCall: Call<MatchResponse>
    @Mock
    private lateinit var matchLeagueCall: Call<MatchLeagueResponse>
    @Mock
    private lateinit var matchResponse: Response<MatchResponse>
    @Mock
    private lateinit var matchLeagueResponse: Response<MatchLeagueResponse>

    @Before
    fun setUp() {
        service = mock(RetrofitService::class.java)

        // leagueSchedulePastMatchesPresenter = mock(LeagueSchedulePastMatchesPresenter::class.java)
        leagueSchedulePastMatchesPresenter = LeagueSchedulePastMatchesPresenter(leagueScheduleView, TestContextProvider())
        leagueScheduleUpcomingMatchesPresenter = LeagueScheduleUpcomingMatchesPresenter(leagueScheduleView, TestContextProvider())
        matchSearchPresenter = MatchSearchPresenter(matchSearchView, TestContextProvider())

        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadLeagueSchedulePastMatchesTest() {
        val matchesList = listOf<Match>()

        runBlocking {
            `when`(service.getPastLeagueMatches(leagueId)).thenReturn(matchLeagueCall)
            //`when`(matchLeagueCall.awaitResponse()).thenReturn(matchLeagueResponse)
            `when`(leagueSchedulePastMatchesPresenter.getFetchedMatches(leagueId)).thenReturn(matchesList)

            leagueSchedulePastMatchesPresenter.loadMatchesByLeague(leagueId, leagueName)

            verify(leagueScheduleView).loadMatchesByLeague(matchesList, leagueName)
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
