package com.pichurchyk.testeventapp.domain.repository

import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import kotlinx.coroutines.flow.Flow

interface EventsRepository {

    suspend fun loadEvents(): Flow<List<EventEntity>>
}
