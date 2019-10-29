package com.calvintd.kade.soccerbase

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.calvintd.kade.soccerbase.activity.MatchSearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchSearchActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MatchSearchActivity::class.java)

    private val testTeamName = "Arsenal"

    @Test
    fun matchSearchingTest() {
        onView(withId(R.id.svMatchSearchView))
            .perform(ViewActions.click())
        onView(isAssignableFrom(EditText.class)).perform(ViewActions.typeText(testTeamName), ViewActions.pressImeActionButton())
    }
}