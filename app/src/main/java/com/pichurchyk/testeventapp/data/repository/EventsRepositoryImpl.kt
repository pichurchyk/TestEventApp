package com.pichurchyk.testeventapp.data.repository

import com.pichurchyk.testeventapp.data.Resource
import com.pichurchyk.testeventapp.data.mapper.EventMapper
import com.pichurchyk.testeventapp.data.source.EventsRemoteDataSource
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.domain.repository.EventsRepository
import com.pichurchyk.testeventapp.presentation.ExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class EventsRepositoryImpl(
    private val remoteDataSource: EventsRemoteDataSource,
    private val coroutineContext: CoroutineContext,
) : EventsRepository {

    override suspend fun loadEvents(): Flow<Resource<List<EventEntity>>> = flow {
        val response = remoteDataSource.loadEvents()

        val result = response.body()

        if (response.isSuccessful && result != null) {
            val events = result.map { eventResponse ->
                EventMapper.mapFrom(eventResponse)
            }
            emit(Resource.Success(events))
        } else {
            emit(Resource.Error(ExceptionHandler(Throwable(response.errorBody().toString()))))
        }
    }.flowOn(coroutineContext)
}
