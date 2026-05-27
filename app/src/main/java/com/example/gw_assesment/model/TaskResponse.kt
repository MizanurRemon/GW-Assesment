package com.example.gw_assesment.model

data class TaskResponse(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: String,
    val status: String
)
