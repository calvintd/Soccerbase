package com.calvintd.kade.soccerbase.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*

class MatchDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        constraintLayout {
            lparams(width = matchParent, height = matchParent)
            padding = 16


        }
    }
}