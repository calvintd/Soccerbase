package com.calvintd.kade.soccerbase.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.itemmodel.Team
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

class TeamAdapter (private val teams: List<Team>, private val detailsListener: (Team) -> Unit,
                   private val playersListener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(TeamUI().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(teams[position], detailsListener, playersListener)

    override fun getItemCount(): Int = teams.size

    class TeamUI : AnkoComponent<ViewGroup> {
        companion object {
            const val iconSize = 40
            const val badgeSize = 160
            const val iconPadding = 6
            const val buttonTextSize = 12f
        }

        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {


        fun bindItem (team: Team, detailsListener: (Team) -> Unit, playersListener: (Team) -> Unit) {


        }
    }
}