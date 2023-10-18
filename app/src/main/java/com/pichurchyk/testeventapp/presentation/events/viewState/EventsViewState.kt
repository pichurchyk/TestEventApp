package com.pichurchyk.testeventapp.presentation.events.viewState

import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.presentation.ExceptionHandler

data class EventsViewState(
    val loader: Loader = Loader(),
    val exception: ExceptionHandler? = null,
    val events: List<EventEntity> = emptyList(),
)
