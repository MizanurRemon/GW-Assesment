package com.example.gw_assesment.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gw_assesment.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel(){

    var state by mutableStateOf(DetailsState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {

    }

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.OnUpdateClick-> {

            }

            is DetailsEvent.OnStatusSelection-> {
                state = state.copy(
                    selectedItem = event.value
                )
            }
        }
    }
}