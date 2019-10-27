package com.calvintd.kade.soccerbase

import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.api.RetrofitService
import com.calvintd.kade.soccerbase.itemmodel.LeagueResponse
import com.calvintd.kade.soccerbase.itemmodel.MatchResponse
import com.calvintd.kade.soccerbase.presenter.LeagueListingPresenter
import com.calvintd.kade.soccerbase.presenter.LeagueSchedulePastMatchesPresenter
import com.calvintd.kade.soccerbase.presenter.LeagueScheduleUpcomingMatchesPresenter
import com.calvintd.kade.soccerbase.presenter.MatchSearchPresenter
import com.calvintd.kade.soccerbase.view.LeagueListingView
import com.calvintd.kade.soccerbase.view.LeagueScheduleView
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiUnitTest {
    @Mock
    private lateinit var service: RetrofitService

    @Mock
    private lateinit var leagueListingPresenter: LeagueListingPresenter
    @Mock
    private lateinit var leagueSchedulePastMatchesPresenter: LeagueSchedulePastMatchesPresenter
    @Mock
    private lateinit var leagueScheduleUpcomingMatchesPresenter: LeagueScheduleUpcomingMatchesPresenter
    @Mock
    private lateinit var matchSearchPresenter: MatchSearchPresenter

    @Mock
    private lateinit var leagueListingView: LeagueListingView
    @Mock
    private lateinit var leagueScheduleView: LeagueScheduleView
    @Mock
    private lateinit var matchSearchView: MatchSearchView

    @Mock
    private lateinit var leagueResponse: Response<LeagueResponse>
    @Mock
    private lateinit var matchResponse: Response<MatchResponse>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        service = RetrofitInstance.getInstance()
        leagueListingPresenter = LeagueListingPresenter(leagueListingView)
        leagueSchedulePastMatchesPresenter = LeagueSchedulePastMatchesPresenter(leagueScheduleView)
        leagueScheduleUpcomingMatchesPresenter = LeagueScheduleUpcomingMatchesPresenter(leagueScheduleView)
        matchSearchPresenter = MatchSearchPresenter(matchSearchView)
    }

    @Test
    fun loadLeagueListingTest() {
        runBlocking {
            verify(leagueListingView).loadData(leagueListingPresenter.getFetchedLeagues())
            verify(leagueListingView).showResponseError(
                leagueResponse.code(),
                leagueResponse.errorBody()
            )
            verify(leagueListingView).showException(HttpException(leagueResponse))
        }
    }

    @Test
    fun loadLeagueSchedulePastMatchesTest() {
        runBlocking {

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
