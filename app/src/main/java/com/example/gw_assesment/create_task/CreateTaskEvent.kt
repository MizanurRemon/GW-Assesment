package com.example.gw_assesment.create_task

sealed class CreateTaskEvent {
    data object OnSubmit: CreateTaskEvent()
}