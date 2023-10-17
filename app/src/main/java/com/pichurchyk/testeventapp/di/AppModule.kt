package com.pichurchyk.testeventapp.di

import com.pichurchyk.testeventapp.data.repository.EventsRepositoryImpl
import com.pichurchyk.testeventapp.domain.repository.EventsRepository
import com.pichurchyk.testeventapp.domain.useCase.GetEventsUseCase
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<EventsRepository> {
        EventsRepositoryImpl(remoteDataSource = get(), coroutineContext = Dispatchers.IO)
    }

    single {
        GetEventsUseCase(eventsRepository = get())
    }
}
