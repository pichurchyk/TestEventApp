package com.pichurchyk.testeventapp.data.io

data class ScheduleEventResponse(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val date: String,
    val imageUrl: String?,
)
