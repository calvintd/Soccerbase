package com.calvintd.kade.soccerbase.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object MatchDateTimeFormatter {
    fun format(date: String?, time: String?): Pair<String, String> {
        val gmt = 7L

        val matchDate = LocalDate.parse(date)
        val matchTime: LocalTime = try {
            OffsetTime.parse(time).toLocalTime()
        } catch (e: DateTimeParseException) {
            LocalTime.parse(time)
        }
        val matchDateTime = LocalDateTime.of(matchDate, matchTime).plusHours(gmt)

        val matchLocalDate = matchDateTime.toLocalDate()
        val matchLocalTime = matchDateTime.toLocalTime()

        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM u")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        val parsedDate = matchLocalDate.format(dateFormatter)
        val parsedTime = matchLocalTime.format(timeFormatter)

        return Pair(parsedDate, parsedTime)
    }
}