package com.calvintd.kade.soccerbase.activity.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.Team
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.scrollView

class TeamDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.team_details_activity_title)

        val team = intent.getParcelableExtra("team") as Team
        
        scrollView {
            constraintLayout {
                lparams(width = matchParent, height = matchParent)

            }
        }
    }
}