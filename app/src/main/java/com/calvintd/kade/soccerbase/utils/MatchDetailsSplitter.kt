package com.calvintd.kade.soccerbase.utils

object MatchDetailsSplitter {
    fun split (detailsSource: String?): List<String> {
        var detailsSplit = emptyList<String>()

        if (detailsSource != null) {
            detailsSplit = detailsSource.split(";")
        }

        return detailsSplit
    }
}