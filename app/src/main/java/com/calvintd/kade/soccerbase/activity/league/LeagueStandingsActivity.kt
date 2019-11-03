package com.calvintd.kade.soccerbase.activity.league

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calvintd.kade.soccerbase.R

class LeagueStandingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.league_standings_activity_title)
    }
}