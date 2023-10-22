package com.pichurchyk.testeventapp.domain.entity.schedule

import com.pichurchyk.testeventapp.domain.entity.date.EventDate

data class ScheduleEventEntity(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val date: EventDate,
    val imageURL: String?,
)
