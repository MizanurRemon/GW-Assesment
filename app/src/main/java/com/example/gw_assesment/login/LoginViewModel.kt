package com.example.gw_assesment.login

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
class LoginViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    init {

    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnSubmitEvent -> {

            }

            is LoginEvent.OnEmailEnter-> {
                state = state.copy(email = event.value)
            }

            is LoginEvent.OnPasswordEnter-> {
                state = state.copy(password = event.value)
            }
        }
    }
}