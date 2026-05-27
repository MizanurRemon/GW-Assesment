package com.example.gw_assesment.details

sealed class DetailsEvent {
    data class OnStatusSelection(val value: String): DetailsEvent()
    data object OnUpdateClick: DetailsEvent()
}