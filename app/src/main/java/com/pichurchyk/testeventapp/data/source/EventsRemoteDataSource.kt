package com.pichurchyk.testeventapp.data.source

import com.pichurchyk.testeventapp.data.io.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface EventsRemoteDataSource {

    @GET(LOAD_EVENTS)
    suspend fun loadEvents(): Response<List<EventResponse>>

    companion object {
        private const val LOAD_EVENTS = "getEvents"
        const val REMOTE_BASE_URL = "https://us-central1-dazn-sandbox.cloudfunctions.net/"
    }
}
