package com.pichurchyk.testeventapp.domain.repository

import com.pichurchyk.testeventapp.data.Resource
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import kotlinx.coroutines.flow.Flow

interface EventsRepository {

    suspend fun loadEvents(): Flow<Resource<List<EventEntity>>>
}
