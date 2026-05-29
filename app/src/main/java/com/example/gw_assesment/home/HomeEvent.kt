package com.example.gw_assesment.home

sealed class HomeEvent {
    data object OnRefresh: HomeEvent()
}