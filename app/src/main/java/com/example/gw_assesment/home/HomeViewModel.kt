package com.example.gw_assesment.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gw_assesment.repository.TaskRepository
import com.example.gw_assesment.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            state = state.copy(loading = true)
            taskRepository.getTasks().onSuccess { tasks ->
                state = state.copy(
                    taskList = tasks,
                    loading = false
                )
            }.onFailure {
                state = state.copy(loading = false)
            }
        }
    }
}
