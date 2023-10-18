package com.pichurchyk.testeventapp.domain.entity.event

import com.pichurchyk.testeventapp.domain.entity.date.EventDate

data class EventEntity(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val date: EventDate,
    val imageURL: String?,
    val videoURL: String?,
)
