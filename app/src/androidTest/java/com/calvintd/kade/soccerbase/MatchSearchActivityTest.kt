package com.calvintd.kade.soccerbase

import androidx.test.espresso.Espresso.*
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

    @Test
    fun matchSearchingTest() {

    }
}