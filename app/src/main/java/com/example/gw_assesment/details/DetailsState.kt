package com.example.gw_assesment.details

import com.example.gw_assesment.utils.StatusType

data class DetailsState(
    val isLoading: Boolean = false,
    val statusList : List<String> = STATUS_LIST,
    val selectedItem: String = StatusType.PENDING.status
)

val STATUS_LIST = listOf(
    StatusType.PENDING.status,
    StatusType.IN_PROGRESS.status,
    StatusType.COMPLETED.status
)
