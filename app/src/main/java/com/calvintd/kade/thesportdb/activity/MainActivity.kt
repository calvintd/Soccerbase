package com.calvintd.kade.thesportdb.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.thesportdb.R
import com.calvintd.kade.thesportdb.presenter.MainPresenter
import com.calvintd.kade.thesportdb.view.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity(), MainView {
    private var recyclerView: RecyclerView = RecyclerView(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = resources.getText(R.string.main_activity_title)

        val presenter = MainPresenter(this, this.resources)

        scrollView{
            verticalLayout{
                lparams(width = matchParent, height = matchParent)

                recyclerView = recyclerView{
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        }

        presenter.loadData(recyclerView)
    }

    override fun loadData() {
        recyclerView.adapter?.notifyDataSetChanged()
    }
}
