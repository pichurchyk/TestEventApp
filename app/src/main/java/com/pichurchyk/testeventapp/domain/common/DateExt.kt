package com.pichurchyk.testeventapp.domain.common

import com.pichurchyk.testeventapp.domain.common.DateUtils.DATE_FORMAT
import com.pichurchyk.testeventapp.domain.common.DateUtils.TIME_FORMAT
import com.pichurchyk.testeventapp.domain.common.DateUtils.inSimpleDateFormat
import com.pichurchyk.testeventapp.domain.entity.date.DateType
import com.pichurchyk.testeventapp.domain.entity.date.EventDate
import org.joda.time.DateTime

fun EventDate.getTodayOrYesterday(currentDate: DateTime): DateType? {
    val currentDay = currentDate.dayOfYear
    val eventDay = date.dayOfYear

    val currentYear = currentDate.year
    val eventYear = date.year

    return if (currentYear == eventYear) {
        when {
            currentDay - eventDay == 1 -> {
                DateType.YESTERDAY
            }

            currentDay == eventDay -> {
                DateType.TODAY
            }

            else -> null
        }
    } else {
        null
    }
}

fun EventDate.getEventDay(): String = date.toDate().inSimpleDateFormat(DATE_FORMAT)

fun EventDate.getEventTime(): String = date.toDate().inSimpleDateFormat(TIME_FORMAT)
