package com.example.gw_assesment.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gw_assesment.datastore.PreferenceManager
import com.example.gw_assesment.utils.Route
import com.example.gw_assesment.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(2000L)
            val isLoggedIn = preferenceManager.isLoggedIn.first()
            Log.d("dataxx", "$isLoggedIn")
            if (isLoggedIn) {
                _uiEvent.emit(UiEvent.Navigation(Route.HOME))
            } else {
                _uiEvent.emit(UiEvent.Navigation(Route.LOGIN))
            }
        }
    }
}