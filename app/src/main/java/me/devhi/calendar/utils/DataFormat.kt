package me.devhi.calendar.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.util.Calendar
import java.util.GregorianCalendar


const val CALENDAR_HEADER_FORMAT = "yyyy년 MM월"
const val DAY_FORMAT = "d"

fun getDate(date: Long, pattern: String?): String {
    return try {
        val formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
        formatter.format(Date(date)).uppercase(Locale.ROOT)
    } catch (e: Exception) {
        ""
    }
}

fun GregorianCalendar.toDayString(): String {
    try {
        val gregorianCalendar = GregorianCalendar(
            this[Calendar.YEAR],
            this[Calendar.MONTH],
            this[Calendar.DAY_OF_MONTH],
            0,
            0,
            0
        )
        return getDate(
            gregorianCalendar.timeInMillis,
            DAY_FORMAT
        )
    } catch (e: Exception) {
        return ""
    }
}