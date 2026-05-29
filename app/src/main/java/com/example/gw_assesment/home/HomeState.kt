package com.example.gw_assesment.home

import com.example.gw_assesment.model.TaskResponse
import com.example.gw_assesment.utils.StatusType
import com.example.gw_assesment.utils.getCurrentDate

data class HomeState(
    val isLoading: Boolean = false,
    val userName: String = "John Doe",
    val taskList: List<TaskResponse> = emptyList()
)

val TASK_LIST = listOf(
    TaskResponse(
        id = 0,
        title = "Call Client",
        description = "",
        dueDate = getCurrentDate(),
        stage = StatusType.IN_PROGRESS.status
    ),
    TaskResponse(
        id = 1,
        title = "Prepare Report",
        description = "",
        dueDate = getCurrentDate(),
        stage = StatusType.IN_PROGRESS.status
    ),
    TaskResponse(
        id = 2,
        title = "Update Website",
        description = "",
        dueDate = getCurrentDate(),
        stage = StatusType.IN_PROGRESS.status
    ),
    TaskResponse(
        id = 3,
        title = "Submit Invoice",
        description = "",
        dueDate = getCurrentDate(),
        stage = StatusType.IN_PROGRESS.status
    ),
)
