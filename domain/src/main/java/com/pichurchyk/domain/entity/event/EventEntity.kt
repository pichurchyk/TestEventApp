package com.pichurchyk.domain.entity.event

data class EventEntity(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val date: String,
    val imageURL: String,
    val videoURL: String,
)
