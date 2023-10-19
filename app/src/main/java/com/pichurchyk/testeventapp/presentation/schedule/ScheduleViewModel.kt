package com.pichurchyk.testeventapp.presentation.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pichurchyk.testeventapp.data.Resource
import com.pichurchyk.testeventapp.domain.useCase.GetScheduledEventsUseCase
import com.pichurchyk.testeventapp.presentation.ExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getScheduledEventsUseCase: GetScheduledEventsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ScheduleViewState())
    val state = _state.asStateFlow()

    private var autoUpdateJob: Job? = null

    init {
        loadEvents()
    }

    private fun setupBackgroundUpdate() {
        if (autoUpdateJob == null) {
            autoUpdateJob = viewModelScope.launch {
                while (true) {
                    delay(BACKGROUND_UPDATE_TIMEOUT)
                    loadEvents(true)
                }
            }
        }
    }

    private fun stopBackgroundUpdate() {
        autoUpdateJob = null
    }

    fun loadEvents(isBackground: Boolean = false) {
        viewModelScope.launch {
            getScheduledEventsUseCase.getEvents()
                .onStart {
                    _state.update { currentState ->
                        currentState.copy(
                            isLoading = !isBackground,
                            exception = null,
                        )
                    }
                }
                .catch {
                    _state.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            exception = ExceptionHandler(it),
                        )
                    }

                    stopBackgroundUpdate()
                }
                .collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            _state.update { currentState ->
                                currentState.copy(
                                    isLoading = false,
                                    exception = null,
                                    events = response.data,
                                )
                            }

                            setupBackgroundUpdate()
                        }

                        is Resource.Error -> {
                            _state.update { currentState ->
                                currentState.copy(
                                    isLoading = false,
                                    exception = response.e,
                                )
                            }

                            stopBackgroundUpdate()
                        }
                    }
                }
        }
    }

    companion object {
        private const val BACKGROUND_UPDATE_TIMEOUT = 30000L
    }
}
