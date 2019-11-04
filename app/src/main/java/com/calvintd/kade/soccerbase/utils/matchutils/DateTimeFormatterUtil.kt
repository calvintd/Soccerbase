package com.calvintd.kade.soccerbase.utils.matchutils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateTimeFormatterUtil {
    private val dateFormatter = DateTimeFormatter.ofPattern("d MMMM u")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

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

        val parsedDate = matchLocalDate.format(dateFormatter)
        val parsedTime = matchLocalTime.format(timeFormatter)

        return Pair(parsedDate, parsedTime)
    }

    fun dateFormat(date: String?): String = LocalDate.parse(date).format(dateFormatter)
}