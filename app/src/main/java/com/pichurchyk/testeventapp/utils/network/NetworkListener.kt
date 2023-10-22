package com.pichurchyk.testeventapp.utils.network

import kotlinx.coroutines.flow.Flow

interface NetworkListener {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}
