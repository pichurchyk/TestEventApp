package com.pichurchyk.testeventapp.presentation.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pichurchyk.testeventapp.data.Resource
import com.pichurchyk.testeventapp.domain.useCase.GetEventsUseCase
import com.pichurchyk.testeventapp.presentation.ExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventsViewModel(
    private val getEventsUseCase: GetEventsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(EventsViewState())
    val state = _state.asStateFlow()

    fun loadEvents() {
        viewModelScope.launch {
            getEventsUseCase.getEvents()
                .onStart {
                    _state.update { currentState ->
                        currentState.copy(isLoading = true, exception = null)
                    }
                }
                .catch {
                    _state.update { currentState ->
                        currentState.copy(isLoading = false, exception = ExceptionHandler(it))
                    }
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
                        }

                        is Resource.Error -> {
                            _state.update { currentState ->
                                currentState.copy(
                                    isLoading = false,
                                    exception = response.e,
                                )
                            }
                        }
                    }
                }
        }
    }
}
