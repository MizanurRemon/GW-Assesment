package com.example.gw_assesment.home

import com.example.gw_assesment.model.TaskResponse

data class HomeState(
    val loading: Boolean = false,
    val taskList: List<TaskResponse> = emptyList()
)

val TASK_LIST = listOf(
    TaskResponse(
        id = 0,
        title = "Call Client",
        description = "",
        dueDate = "mar 15, 2026",
        status = "Pending"
    ),
    TaskResponse(
        id = 1,
        title = "Prepare Report",
        description = "",
        dueDate = "mar 15, 2026",
        status = "Completed"
    ),
    TaskResponse(
        id = 2,
        title = "Update Website",
        description = "",
        dueDate = "mar 15, 2026",
        status = "In Progress"
    ),
    TaskResponse(
        id = 3,
        title = "Submit Invoice",
        description = "",
        dueDate = "mar 15, 2026",
        status = "Pending"
    ),
)
