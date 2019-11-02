package com.calvintd.kade.soccerbase.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calvintd.kade.soccerbase.itemmodel.Player
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

class PlayerAdapter (private val players: List<Player>) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(PlayerUI().createView(AnkoContext.Companion.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(players[position])

    override fun getItemCount(): Int = players.size

    class PlayerUI : AnkoComponent<ViewGroup> {
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

        fun bindItem (player: Player) {

        }
    }
}