package com.calvintd.kade.soccerbase.activity.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calvintd.kade.soccerbase.R
import com.calvintd.kade.soccerbase.itemmodel.Player
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.scrollView

class PlayerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.player_details_activity_title)

        val player = intent.getParcelableExtra("player") as Player

        scrollView {
            constraintLayout {
                lparams(width = matchParent, height = matchParent)

            }
        }
    }
}