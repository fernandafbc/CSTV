package com.fernanda.domain.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DAY_MONTH_YEAR_FORMAT = "dd/MM/yyyy"
const val DAY_NAME_HOUR_FORMAT = "EEE, HH:mm"
const val DAY_MONTH_HOUR_FORMAT = "dd.MM HH:mm"
const val HOUR_FORMAT = "HH:mm"

fun String.toSafeDate(format: String = DEFAULT_DATE_FORMAT): Date? {
    return try {
        val sdf = SimpleDateFormat(format, Locale("pt", "BR"))
        sdf.parse(this)
    } catch (e: Exception) {
        null
    }
}

private fun isToday(date: String): Boolean {
    val currentDate = Calendar.getInstance().time

    return currentDate.format(DAY_MONTH_YEAR_FORMAT) == date.toSafeDate()
        ?.format(DAY_MONTH_YEAR_FORMAT)
}

private fun isDateInCurrentWeek(date: String): Boolean {
    val currentCalendar = Calendar.getInstance()
    val week = currentCalendar[Calendar.WEEK_OF_YEAR]
    val year = currentCalendar[Calendar.YEAR]
    val targetCalendar = Calendar.getInstance()
    targetCalendar.time = date.toSafeDate()
    val targetWeek = targetCalendar[Calendar.WEEK_OF_YEAR]
    val targetYear = targetCalendar[Calendar.YEAR]
    return week == targetWeek && year == targetYear
}

fun String.getDate(todayString: String): String {
    return when {
        isToday(this) -> todayString + ", " + this.getHour()
        isDateInCurrentWeek(this) -> this.toSafeDate()?.format(DAY_NAME_HOUR_FORMAT)?.capitalized()
            .orEmpty()

        else -> this.toSafeDate()?.format(DAY_MONTH_HOUR_FORMAT).orEmpty()
    }
}

private fun String.getHour() = this.toSafeDate()?.format(HOUR_FORMAT).orEmpty()

private fun Date.format(format: String): String = SimpleDateFormat(
    format,
    Locale("pt", "BR")
).format(this)