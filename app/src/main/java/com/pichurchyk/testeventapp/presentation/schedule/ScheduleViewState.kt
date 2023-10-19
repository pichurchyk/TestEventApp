package com.pichurchyk.testeventapp.presentation.schedule

import com.pichurchyk.testeventapp.domain.entity.schedule.ScheduleEventEntity
import com.pichurchyk.testeventapp.presentation.ExceptionHandler
import com.pichurchyk.testeventapp.presentation.events.viewState.Loader

data class ScheduleViewState(
    val loader: Loader = Loader(),
    val exception: ExceptionHandler? = null,
    val events: List<ScheduleEventEntity> = emptyList(),
)
