package com.pichurchyk.testeventapp.domain.useCase

import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow

class GetEventsUseCase(private val eventsRepository: EventsRepository) {

    suspend fun getEvents(): Flow<List<EventEntity>> = eventsRepository.loadEvents()
}
