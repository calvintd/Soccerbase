package com.calvintd.kade.soccerbase.itemmodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class League (
    var leagueId: Int? = null,
    var name: String? = null,
    var badge: String? = null,
    var description: String? = null): Parcelable