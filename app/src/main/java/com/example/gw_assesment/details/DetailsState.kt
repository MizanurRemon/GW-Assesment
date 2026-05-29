package com.example.gw_assesment.details

import com.example.gw_assesment.model.TaskResponse
import com.example.gw_assesment.utils.StatusType

data class DetailsState(
    val isLoading: Boolean = false,
    val statusList : List<String> = STATUS_LIST,
    val selectedItem: String = StatusType.IN_PROGRESS.status,
    val taskResponse: TaskResponse = TaskResponse(
        title = "",
        description = "",
        dueDate = "",
        stage = ""
    )
)

val STATUS_LIST = listOf(
    StatusType.IN_PROGRESS.status,
    StatusType.COMPLETED.status
)
