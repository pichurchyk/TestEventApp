package com.pichurchyk.testeventapp.data.source

import com.pichurchyk.testeventapp.data.io.EventResponse
import com.pichurchyk.testeventapp.data.io.ScheduleEventResponse
import retrofit2.Response
import retrofit2.http.GET

interface EventsRemoteDataSource {

    @GET(LOAD_EVENTS)
    suspend fun loadEvents(): Response<List<EventResponse>>

    @GET(LOAD_SCHEDULE)
    suspend fun loadScheduledEvents(): Response<List<ScheduleEventResponse>>

    companion object {
        private const val LOAD_EVENTS = "getEvents"
        private const val LOAD_SCHEDULE = "getSchedule"
        const val REMOTE_BASE_URL = "https://us-central1-dazn-sandbox.cloudfunctions.net/"
    }
}
