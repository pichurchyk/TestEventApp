package com.pichurchyk.testeventapp

import com.pichurchyk.testeventapp.domain.common.getTodayOrYesterday
import com.pichurchyk.testeventapp.domain.entity.date.DateType
import com.pichurchyk.testeventapp.domain.entity.date.EventDate
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.joda.time.DateTime

class DateExtTest : StringSpec({

    val eventDate = EventDate(DateTime("2023-10-18T01:04:45.407Z"))
    val dayBeforeEvent = DateTime("2023-10-19T01:04:45.407Z")
    val sameDay = DateTime("2023-10-18T11:04:45.407Z")
    val yearAfterEvent = DateTime("2024-10-18T11:04:45.407Z")

    "value should be null because event was year ago" {
        val todayOrYesterday = eventDate.getTodayOrYesterday(yearAfterEvent)

        todayOrYesterday shouldBe null
    }

    "value should be DateType.YESTERDAY because event was day before current" {
        val todayOrYesterday = eventDate.getTodayOrYesterday(dayBeforeEvent)

        todayOrYesterday shouldBe DateType.YESTERDAY
    }

    "value should be DateType.TODAY because event was day before current" {
        val todayOrYesterday = eventDate.getTodayOrYesterday(sameDay)

        todayOrYesterday shouldBe DateType.TODAY
    }
})
