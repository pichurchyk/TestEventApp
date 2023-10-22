package com.pichurchyk.testeventapp.domain.common

import android.annotation.SuppressLint
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {
    const val DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm"
    const val DATE_FORMAT = "dd.MM.yyyy"
    const val TIME_FORMAT = "HH:mm"

    fun parseDate(dateString: String): DateTime {
        val parser = ISODateTimeFormat.dateTimeParser()
        return parser.parseDateTime(dateString)
    }

    @SuppressLint("SimpleDateFormat")
    fun Date.inSimpleDateFormat(format: String): String = SimpleDateFormat(format).format(this)
}
