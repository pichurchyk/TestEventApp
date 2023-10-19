package com.pichurchyk.testeventapp.domain.useCase

import com.pichurchyk.testeventapp.data.Resource
import com.pichurchyk.testeventapp.domain.entity.schedule.ScheduleEventEntity
import com.pichurchyk.testeventapp.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow

class GetScheduledEventsUseCase(private val eventsRepository: EventsRepository) {

    suspend fun getEvents(): Flow<Resource<List<ScheduleEventEntity>>> =
        eventsRepository.loadScheduledEvents()
}
