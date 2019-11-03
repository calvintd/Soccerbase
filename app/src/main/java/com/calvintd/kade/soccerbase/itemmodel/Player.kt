package com.calvintd.kade.soccerbase.itemmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Player (
    var playerId: Int? = null,
    var playerNationality: String? = null,
    var playerName: String? = null,
    var playerTeam: String? = null,
    var playerBirthDate: String? = null,
    var playerBirthLocation: String? = null,
    var playerSignedDate: String? = null,
    var playerWage: String? = null,
    var playerDescription: String? = null,
    var playerPosition: String? = null,
    var playerThumbnail: String? = null,
    var playerFanart: String? = null
): Parcelable