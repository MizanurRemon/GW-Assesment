package com.example.gw_assesment.create_task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gw_assesment.repository.TaskRepository
import com.example.gw_assesment.utils.UiEvent
import com.example.gw_assesment.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    var state by mutableStateOf(CreateTaskState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {

    }

    fun onEvent(event: CreateTaskEvent) {
        when (event) {
            is CreateTaskEvent.OnSubmit -> {
                if (state.title.isNotBlank()) {
                    createTask()
                } else {
                    state = state.copy(error = "Title cannot be empty")
                }
            }

            is CreateTaskEvent.OnTitleEnter -> {
                state = state.copy(
                    title = event.value,
                    error = null
                )
            }

            is CreateTaskEvent.OnDescriptionEnter -> {
                state = state.copy(
                    description = event.value
                )
            }

            is CreateTaskEvent.OnDateSelection -> {
                state = state.copy(
                    dueDate = event.value,
                    isDialogOpen = false
                )
            }

            is CreateTaskEvent.OnDateSelectionDialog -> {
                state = state.copy(isDialogOpen = event.value)
            }

        }
    }

    private fun createTask() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            repository.createTask(
                name = state.title,
                description = state.description,
                dueDate = state.dueDate
            ).onSuccess {
                state = state.copy(isLoading = false)
                _uiEvent.emit(UiEvent.Success)
            }.onFailure { e ->
                Log.d("dataxx", "createTaskVM: $e")
                state = state.copy(isLoading = false, error = e.message ?: "An error occurred")
                _uiEvent.emit(
                    UiEvent.ShowSnackBar(
                        UiText.DynamicString(e.message ?: "An error occurred")
                    )
                )
            }
        }
    }
}