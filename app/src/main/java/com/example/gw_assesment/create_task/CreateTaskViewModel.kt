package com.example.gw_assesment.create_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gw_assesment.home.HomeState
import com.example.gw_assesment.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


@HiltViewModel
class CreateTaskViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(CreateTaskState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {

    }

    fun onEvent(event: CreateTaskEvent) {
        when (event) {
            is CreateTaskEvent.OnSubmit -> {
                if(!state.title.isEmpty()){

                }
            }

            is CreateTaskEvent.OnTitleEnter-> {
                state = state.copy(
                    title = event.value
                )
            }

            is CreateTaskEvent.OnDescriptionEnter-> {
                state = state.copy(
                    description = event.value
                )
            }

            is CreateTaskEvent.OnDateSelection-> {
                state = state.copy(
                    dueDate = event.value,
                    isDialogOpen = false
                )
            }

            is CreateTaskEvent.OnDateSelectionDialog-> {
                state = state.copy(isDialogOpen = event.value)
            }

        }
    }
}