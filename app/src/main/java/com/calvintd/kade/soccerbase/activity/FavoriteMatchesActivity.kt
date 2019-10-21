package com.calvintd.kade.soccerbase.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FavoriteMatchesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getString(R.string.favorite_matches_activity_title)

        scrollView {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)

                recyclerView = recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@FavoriteMatchesActivity)
                }
            }
        }
    }
}