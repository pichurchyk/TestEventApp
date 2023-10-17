package com.pichurchyk.domain.useCase

import com.pichurchyk.domain.repository.EventsRepository

class GetEventsUseCase(private val eventsRepository: EventsRepository) {

    fun getEvents() = eventsRepository.loadEvents()
}
