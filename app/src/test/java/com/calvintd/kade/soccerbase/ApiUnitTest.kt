package com.calvintd.kade.soccerbase

import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchLeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.presenter.LeagueListingPresenter
import com.calvintd.kade.soccerbase.presenter.LeagueSchedulePastMatchesPresenter
import com.calvintd.kade.soccerbase.presenter.LeagueScheduleUpcomingMatchesPresenter
import com.calvintd.kade.soccerbase.presenter.MatchSearchPresenter
import com.calvintd.kade.soccerbase.repository.*
import com.calvintd.kade.soccerbase.view.LeagueListingView
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import com.calvintd.kade.soccerbase.view.MatchSearchView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiUnitTest {
    private val leagueId = 4328
    private val query = "Chelsea"
    private val testContextProvider = TestContextProvider()

    @Mock
    private lateinit var leagueListingView: LeagueListingView
    @Mock
    private lateinit var leagueScheduleView: LeagueScheduleView
    @Mock
    private lateinit var matchSearchView: MatchSearchView

    @Mock
    private lateinit var leagueResponseRepository: LeagueResponseRepository
    @Mock
    private lateinit var matchLeagueResponseRepository: MatchLeagueResponseRepository
    @Mock
    private lateinit var matchResponseRepository: MatchResponseRepository

    @Mock
    private lateinit var leagueResponse: LeagueResponse
    @Mock
    private lateinit var matchLeagueResponse: MatchLeagueResponse
    @Mock
    private lateinit var matchResponse: MatchResponse

    private lateinit var leagueListingPresenter: LeagueListingPresenter
    private lateinit var leagueSchedulePastMatchesPresenter: LeagueSchedulePastMatchesPresenter
    private lateinit var leagueScheduleUpcomingMatchesPresenter: LeagueScheduleUpcomingMatchesPresenter
    private lateinit var matchSearchPresenter: MatchSearchPresenter

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        leagueListingPresenter = LeagueListingPresenter(leagueListingView, leagueResponseRepository, testContextProvider)
        leagueSchedulePastMatchesPresenter = LeagueSchedulePastMatchesPresenter(leagueScheduleView, matchLeagueResponseRepository,
            testContextProvider)
        leagueScheduleUpcomingMatchesPresenter = LeagueScheduleUpcomingMatchesPresenter(leagueScheduleView, matchLeagueResponseRepository,
            testContextProvider)
        matchSearchPresenter = MatchSearchPresenter(matchSearchView, matchResponseRepository, testContextProvider)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadLeagueListingTest() {
        runBlockingTest {
            leagueListingPresenter.getFetchedLeagues()
            argumentCaptor<LeagueResponseRepositoryCallback<LeagueResponse>>().apply {
                verify(leagueResponseRepository).getSoccerLeagues(capture())
                firstValue.onDataLoaded(leagueResponse)
            }
            verify(leagueListingView).onDataLoaded(leagueResponse)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadLeagueSchedulePastMatchesTest() {
        runBlockingTest {
            leagueSchedulePastMatchesPresenter.getFetchedMatches(leagueId)
            argumentCaptor<MatchLeagueResponseRepositoryCallback<MatchLeagueResponse>>().apply {
                verify(matchLeagueResponseRepository).getPastLeagueMatches(eq(leagueId), capture())
                firstValue.onDataLoaded(matchLeagueResponse)
            }
            verify(leagueScheduleView).onDataLoaded(matchLeagueResponse)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadLeagueScheduleUpcomingMatchesTest() {
        runBlockingTest {
            leagueScheduleUpcomingMatchesPresenter.getFetchedMatches(leagueId)
            argumentCaptor<MatchLeagueResponseRepositoryCallback<MatchLeagueResponse>>().apply {
                verify(matchLeagueResponseRepository).getUpcomingLeagueMatches(eq(leagueId), capture())
                firstValue.onDataLoaded(matchLeagueResponse)
            }
            verify(leagueScheduleView).onDataLoaded(matchLeagueResponse)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadMatchSearchTest() {
        runBlockingTest {
            matchSearchPresenter.getFetchedMatches(query)
            argumentCaptor<MatchResponseRepositoryCallback<MatchResponse>>().apply {
                verify(matchResponseRepository).getMatchesSearch(eq(query), capture())
                firstValue.onDataLoaded(matchResponse)
            }
            verify(matchSearchView).onDataLoaded(matchResponse)
        }
    }
}
