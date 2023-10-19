package com.pichurchyk.testeventapp

import com.pichurchyk.testeventapp.domain.common.getTodayOrYesterday
import com.pichurchyk.testeventapp.domain.entity.date.EventDate
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.joda.time.DateTime

class DateExtTest : StringSpec({

    val eventDate = EventDate(DateTime("2023-10-18T01:04:45.407Z"))
    val dayBeforeEvent = DateTime("2023-10-17T01:04:45.407Z")
    val sameDay = DateTime("2023-10-17T11:04:45.407Z")
    val yearAfterEvent = DateTime("2024-10-18T11:04:45.407Z")

    "value should be null because event was year ago" {
        val todayOrYesterday = eventDate.getTodayOrYesterday(yearAfterEvent)

        todayOrYesterday shouldBe null
    }
})
