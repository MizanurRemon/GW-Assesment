package com.example.gw_assesment.login

sealed class LoginEvent {
    data class OnEmailEnter(val value: String): LoginEvent()
    data class OnPasswordEnter(val value: String): LoginEvent()
    data object OnSubmitEvent: LoginEvent()
}