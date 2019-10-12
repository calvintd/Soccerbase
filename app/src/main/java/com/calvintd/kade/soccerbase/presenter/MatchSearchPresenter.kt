package com.calvintd.kade.soccerbase.presenter

import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.api.RetrofitInstance
import com.calvintd.kade.soccerbase.model.Match
import com.calvintd.kade.soccerbase.view.MatchSearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MatchSearchPresenter(private val view: MatchSearchView) {
    fun loadMatchByQuery(recyclerView: RecyclerView?, query: String) {
        val matches = ArrayList<Match>()
        val instance = RetrofitInstance.getInstance()

        CoroutineScope(Dispatchers.IO).launch {
            val response = instance.getMatchesSearch(query)
            withContext(Dispatchers.Main) {
                try {

                } catch (e: HttpException) {

                }
            }
        }
    }
}