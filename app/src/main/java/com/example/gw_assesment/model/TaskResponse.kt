package com.example.gw_assesment.model

data class TaskResponse(
    val id: Int = 0,
    val title: String,
    val description: String,
    val dueDate: String,
    val stage: String
)
