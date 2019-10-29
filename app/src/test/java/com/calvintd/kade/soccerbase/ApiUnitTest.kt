package com.calvintd.kade.soccerbase

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.api.RetrofitService
import com.calvintd.kade.soccerbase.itemmodel.Match
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.presenter.LeagueSchedulePastMatchesPresenter
import com.calvintd.kade.soccerbase.presenter.LeagueScheduleUpcomingMatchesPresenter
import com.calvintd.kade.soccerbase.presenter.MatchSearchPresenter
import com.calvintd.kade.soccerbase.utils.FetchMatchesCoroutines
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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
    private val testContextProvider = TestContextProvider()

    @Mock
    private val service: RetrofitService = RetrofitInstance.getInstance()
    @Mock
    private val fetcher = FetchMatchesCoroutines

    private lateinit var leagueSchedulePastMatchesPresenter: LeagueSchedulePastMatchesPresenter
    private lateinit var leagueScheduleUpcomingMatchesPresenter: LeagueScheduleUpcomingMatchesPresenter
    private lateinit var matchSearchPresenter: MatchSearchPresenter

    private val leagueScheduleView: LeagueScheduleView = mock(LeagueScheduleView::class.java)
    private val matchSearchView: MatchSearchView = mock(MatchSearchView::class.java)

    @Mock
    private lateinit var matchResponse: Response<MatchResponse>
    @Mock
    private lateinit var matchLeagueResponse: Response<MatchLeagueResponse>

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        // Dispatchers.setMain(Dispatchers.Unconfined)

        leagueScheduleUpcomingMatchesPresenter = LeagueScheduleUpcomingMatchesPresenter(leagueScheduleView, testContextProvider)
        matchSearchPresenter = MatchSearchPresenter(matchSearchView, testContextProvider)

        MockitoAnnotations.initMocks(this)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadLeagueSchedulePastMatchesTest() {
        val matchesList = listOf<Match>()
        leagueSchedulePastMatchesPresenter = LeagueSchedulePastMatchesPresenter(leagueScheduleView, testContextProvider)

        runBlockingTest {
            `when`(service.getPastLeagueMatches(leagueId)).thenReturn(matchLeagueResponse)

            leagueSchedulePastMatchesPresenter.loadMatchesByLeague(leagueId, leagueName)
            `when`(fetcher.getFetchedMatchesLeagueSchedule(leagueScheduleView, matchLeagueResponse)).thenReturn(matchesList)

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
}
