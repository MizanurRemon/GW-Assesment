package com.example.gw_assesment.create_task

import com.example.gw_assesment.utils.getCurrentDate


data class CreateTaskState(
    val loading: Boolean = false,
    val title: String = "",
    val description: String = "",
    val dueDate: String = getCurrentDate()
)