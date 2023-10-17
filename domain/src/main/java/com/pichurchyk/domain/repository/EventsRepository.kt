package com.pichurchyk.domain.repository

import com.pichurchyk.domain.entity.event.EventEntity

interface EventsRepository {

    fun loadEvents(): List<EventEntity>
}
