package com.example.gw_assesment.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gw_assesment.repository.AuthRepository
import com.example.gw_assesment.utils.Route
import com.example.gw_assesment.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    init {

    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnSubmitEvent -> {
                Log.d("dataxx", "onEvent: ${state.toString()}")
                if (!state.email.isEmpty() && !state.password.isEmpty()) {
                    login()
                }
            }

            is LoginEvent.OnEmailEnter-> {
                state = state.copy(email = event.value)
            }

            is LoginEvent.OnPasswordEnter-> {
                state = state.copy(password = event.value)
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            val result = repository.login(
                user = state.email,
                pass = state.password
            )

            result.onSuccess { _ ->
                state = state.copy(isLoading = false)
                _uiEvent.emit(UiEvent.Navigation(Route.HOME))
            }.onFailure { e ->
                state = state.copy(isLoading = false)
            }
        }
    }
}