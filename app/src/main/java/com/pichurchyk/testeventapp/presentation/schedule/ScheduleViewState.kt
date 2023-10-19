package com.pichurchyk.testeventapp.presentation.schedule

import com.pichurchyk.testeventapp.domain.entity.schedule.ScheduleEventEntity
import com.pichurchyk.testeventapp.presentation.ExceptionHandler

data class ScheduleViewState(
    val isLoading: Boolean = false,
    val exception: ExceptionHandler? = null,
    val events: List<ScheduleEventEntity> = emptyList(),
)
