package com.example.gw_assesment.utils

sealed class UiEvent {
    object Success : UiEvent()
    object NavigateUp : UiEvent()
    data class Navigation(val route: String) : UiEvent()

    data class ShowSnackBar(val message: UiText) : UiEvent()
}