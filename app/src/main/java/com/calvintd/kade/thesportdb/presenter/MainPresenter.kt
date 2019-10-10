package com.calvintd.kade.thesportdb.presenter

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.thesportdb.R
import com.calvintd.kade.thesportdb.model.League
import com.calvintd.kade.thesportdb.recyclerview.LeagueAdapter
import com.calvintd.kade.thesportdb.view.MainView

class MainPresenter(private val view: MainView, private val resources: Resources) {
    fun loadData(recyclerView: RecyclerView) {
        val leagues = ArrayList<League>()

        val name = resources.getStringArray(R.array.name)
        val description = resources.getStringArray(R.array.description)

        val logoArray = resources.obtainTypedArray(R.array.logo)
        val logo = ArrayList<Int>()

        logo.let {
            for (i in name.indices) {
                it.add(logoArray.getResourceId(i, 0))
            }
        }

        for (i in name.indices) {
            val leagueItem = League(name[i], logo[i], description[i].replace("\\n{2,}\\s".toRegex(),"\n\n"))
            leagues.add(leagueItem)
        }

        logoArray.recycle()
        recyclerView.adapter = LeagueAdapter(leagues)
        view.loadData()
    }
}