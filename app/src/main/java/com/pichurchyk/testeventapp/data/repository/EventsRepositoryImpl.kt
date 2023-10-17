package com.pichurchyk.testeventapp.data.repository

import com.pichurchyk.testeventapp.data.mapper.EventMapper
import com.pichurchyk.testeventapp.data.source.EventsRemoteDataSource
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class EventsRepositoryImpl(
    private val remoteDataSource: EventsRemoteDataSource,
    private val coroutineContext: CoroutineContext,
) : EventsRepository {

    override suspend fun loadEvents(): Flow<List<EventEntity>> = flow {
        val response = remoteDataSource.loadEvents()
        response.body().map { eventResponse ->
            EventMapper.mapFrom(eventResponse)
        }.also {
            emit(it)
        }
    }.flowOn(coroutineContext)
}
