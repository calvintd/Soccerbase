package com.calvintd.kade.soccerbase

import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.presenter.LeagueListingPresenter
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepository
import com.calvintd.kade.soccerbase.repository.LeagueResponseRepositoryCallback
import com.calvintd.kade.soccerbase.view.LeagueListingView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

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
    private lateinit var leagueListingView: LeagueListingView

    @Mock
    private lateinit var leagueResponseRepository: LeagueResponseRepository

    @Mock
    private lateinit var leagueResponse: LeagueResponse

    private lateinit var leagueListingPresenter: LeagueListingPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        leagueListingPresenter = LeagueListingPresenter(leagueListingView, leagueResponseRepository, testContextProvider)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadLeagueSchedulePastMatchesTest() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        runBlocking {
            leagueListingPresenter.getFetchedLeagues()
            argumentCaptor<LeagueResponseRepositoryCallback<LeagueResponse>>().apply {
                verify(leagueResponseRepository).getSoccerLeagues(capture())
                firstValue.onDataLoaded(leagueResponse)
            }

            verify(leagueListingView).onDataLoaded(leagueResponse)
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
