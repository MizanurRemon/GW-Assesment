package com.example.gw_assesment.create_task

sealed class CreateTaskEvent {
    data class OnTitleEnter(val value: String): CreateTaskEvent()
    data class OnDescriptionEnter(val value: String): CreateTaskEvent()
    data class OnDateSelection(val value: String): CreateTaskEvent()
    data object OnSubmit: CreateTaskEvent()
}