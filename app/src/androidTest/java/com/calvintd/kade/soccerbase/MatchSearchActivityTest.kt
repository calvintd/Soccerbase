package com.calvintd.kade.soccerbase

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.calvintd.kade.soccerbase.activity.MatchSearchActivity
import org.hamcrest.core.StringContains.containsString
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MatchSearchActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MatchSearchActivity::class.java)

    private val testTeamNameFound = "Arsenal"
    private val testTeamNameZero = "Euclidean"

    private val textViewFound = "Displaying search results for \"$testTeamNameFound\":"
    private val textViewZero = "No search results found for \"$testTeamNameZero\"."

    @Test
    fun matchSearchingTestFound() {
        onView(withId(R.id.svMatchSearchView))
            .perform(ViewActions.click())
        onView(isAssignableFrom(EditText::class.java))
            .perform(ViewActions.typeText(testTeamNameFound), ViewActions.pressImeActionButton())
        onView(withId(R.id.pbMatchSearchProgressBar))
            .check(matches(isDisplayed()))
        Thread.sleep(25000)
        onView(withId(R.id.pbMatchSearchProgressBar))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.tvMatchSearchResults))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvMatchSearchResults))
            .check(matches(withText(containsString(textViewFound))))
        onView(withId(R.id.rvMatchSearchRecyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun matchSearchingTestZero() {
        onView(withId(R.id.svMatchSearchView))
            .perform(ViewActions.click())
        onView(isAssignableFrom(EditText::class.java))
            .perform(ViewActions.typeText(testTeamNameZero), ViewActions.pressImeActionButton())
        onView(withId(R.id.pbMatchSearchProgressBar))
            .check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withId(R.id.pbMatchSearchProgressBar))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.tvMatchSearchResults))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvMatchSearchResults))
            .check(matches(withText(containsString(textViewZero))))
        onView(withId(R.id.rvMatchSearchRecyclerView))
            .check(matches(not(isDisplayed())))
    }
}