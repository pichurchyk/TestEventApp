package com.pichurchyk.testeventapp.di

import com.pichurchyk.testeventapp.data.repository.EventsRepositoryImpl
import com.pichurchyk.testeventapp.data.source.EventsRemoteDataSource
import com.pichurchyk.testeventapp.domain.repository.EventsRepository
import com.pichurchyk.testeventapp.domain.useCase.GetEventsUseCase
import com.pichurchyk.testeventapp.presentation.events.EventsViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                },
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(EventsRemoteDataSource.REMOTE_BASE_URL)
            .build()
    }

//    DataSource
    single {
        provideRemoteDataSource(get())
    }

//    Repositories
    single<EventsRepository> {
        EventsRepositoryImpl(remoteDataSource = get(), coroutineContext = Dispatchers.IO)
    }

//    UseCases
    single {
        GetEventsUseCase(eventsRepository = get())
    }

//    ViewModels
    single {
        EventsViewModel(getEventsUseCase = get())
    }
}

fun provideRemoteDataSource(retrofit: Retrofit): EventsRemoteDataSource =
    retrofit.create(EventsRemoteDataSource::class.java)
