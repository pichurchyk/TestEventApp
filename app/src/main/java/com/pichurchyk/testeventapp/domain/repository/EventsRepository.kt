package com.pichurchyk.testeventapp.domain.repository

import com.pichurchyk.testeventapp.data.Resource
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.domain.entity.schedule.ScheduleEventEntity
import kotlinx.coroutines.flow.Flow

interface EventsRepository {

    suspend fun loadEvents(): Flow<Resource<List<EventEntity>>>

    suspend fun loadScheduledEvents(): Flow<Resource<List<ScheduleEventEntity>>>
}
