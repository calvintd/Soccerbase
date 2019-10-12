package com.calvintd.kade.soccerbase.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Team (var teamId: Int?, var name: String?, var badge: String?): Parcelable
