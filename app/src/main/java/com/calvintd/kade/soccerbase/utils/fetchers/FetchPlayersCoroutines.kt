package com.calvintd.kade.soccerbase.utils.fetchers

import com.calvintd.kade.soccerbase.itemmodel.Player
import com.calvintd.kade.soccerbase.itemmodel.PlayerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object FetchPlayersCoroutines {
    suspend fun getFetchedPlayers(data: PlayerResponse?): List<Player> {
        return withContext(Dispatchers.Main) {
            val fetchedPlayers = mutableListOf<Player>()

            if (data?.players != null) {
                val playerResponseItems = data.players
                for (i in playerResponseItems.indices) {
                    val item = playerResponseItems[i]

                    val playerId = item.playerId
                    val playerNationality = item.playerNationality
                    val playerName = item.playerName
                    val playerTeam = item.playerTeam
                    val playerBirthDate = item.playerBirthDate
                    val playerBirthLocation = item.playerBirthLocation
                    val playerSignedDate = item.playerSignedDate
                    val playerWage = item.playerWage
                    val playerDescription = item.playerDescription
                    val playerPosition = item.playerPosition
                    val playerThumbnail = item.playerThumbnail.plus("/preview")
                    val playerFanart = item.playerFanart.plus("/preview")

                    val player = Player(
                        playerId,
                        playerNationality,
                        playerName,
                        playerTeam,
                        playerBirthDate,
                        playerBirthLocation,
                        playerSignedDate,
                        playerWage,
                        playerDescription,
                        playerPosition,
                        playerThumbnail,
                        playerFanart
                    )

                    fetchedPlayers.add(player)
                }
            }
            fetchedPlayers
        }
    }
}