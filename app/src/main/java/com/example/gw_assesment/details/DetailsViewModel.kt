package com.example.gw_assesment.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gw_assesment.model.TaskResponse
import com.example.gw_assesment.repository.TaskRepository
import com.example.gw_assesment.utils.StatusType
import com.example.gw_assesment.utils.UiEvent
import com.example.gw_assesment.utils.UiText
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TaskRepository
) : ViewModel(){

    var state by mutableStateOf(DetailsState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        savedStateHandle.get<String>("task")?.let { taskJson ->
            val task = Gson().fromJson(taskJson, TaskResponse::class.java)
            state = state.copy(
                taskResponse = task,
                selectedItem = StatusType.fromState(task.stage).status
            )
        }
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.OnUpdateClick -> {
                viewModelScope.launch {
                    state = state.copy(isLoading = true)
                    repository.updateTask(
                        taskId = state.taskResponse.id,
                        status = state.selectedItem
                    ).onSuccess {
                        state = state.copy(isLoading = false)
                        _uiEvent.emit(UiEvent.Success)
                    }.onFailure {
                        state = state.copy(isLoading = false)
                        _uiEvent.emit(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(
                                    it.message ?: "Error updating task"
                                )
                            )
                        )
                    }
                }
            }

            is DetailsEvent.OnStatusSelection -> {
                state = state.copy(
                    selectedItem = event.value
                )
            }
        }
    }
}