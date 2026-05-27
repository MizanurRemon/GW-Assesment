package com.example.gw_assesment.details

sealed class DetailsEvent {
    data object OnUpdateClick: DetailsEvent()
}