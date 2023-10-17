package com.pichurchyk.testeventapp.presentation.events

import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.presentation.ExceptionHandler

data class EventsViewState(
    val isLoading: Boolean = false,
    val exception: ExceptionHandler? = null,
    val events: List<EventEntity> = emptyList(),
)
