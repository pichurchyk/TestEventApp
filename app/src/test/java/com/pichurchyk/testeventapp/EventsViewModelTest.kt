package com.pichurchyk.testeventapp

import com.pichurchyk.testeventapp.data.Resource
import com.pichurchyk.testeventapp.domain.entity.event.EventEntity
import com.pichurchyk.testeventapp.domain.repository.EventsRepository
import com.pichurchyk.testeventapp.domain.useCase.GetEventsUseCase
import com.pichurchyk.testeventapp.presentation.events.EventsViewModel
import com.pichurchyk.testeventapp.presentation.events.viewState.EventsViewState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class EventsViewModelTest : StringSpec({

    val repository: EventsRepository = mockk()

    val getEventsUseCase = GetEventsUseCase(repository)
    val dispatcher = UnconfinedTestDispatcher()

    beforeTest {
        Dispatchers.setMain(dispatcher)
    }

    afterTest {
        Dispatchers.resetMain()
    }

    "initial viewModel.state should be with default values of EventsViewState" {
        val viewModel = EventsViewModel(getEventsUseCase)

        viewModel.state.value shouldBe EventsViewState()
    }

    "viewModel.loadEvents should make API call to events and update state" {
        val viewModel = EventsViewModel(getEventsUseCase)

        val expectedResponse = listOf<EventEntity>(mockk(), mockk())

        runTest {
            coEvery {
                repository.loadEvents()
            } returns flowOf(Resource.Success(expectedResponse))
        }

        viewModel.loadEvents()

        viewModel.state.value.events.size shouldBe expectedResponse.size
    }
})
