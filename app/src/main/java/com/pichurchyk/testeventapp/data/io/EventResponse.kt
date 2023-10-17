package com.pichurchyk.testeventapp.data.io

data class EventResponse(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val date: String,
    val imageURL: String,
    val videoURL: String,
)
