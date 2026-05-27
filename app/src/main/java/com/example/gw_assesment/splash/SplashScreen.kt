package com.example.gw_assesment.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.gw_assesment.R
import com.example.gw_assesment.utils.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun SplashScreen(
    uiEvent: Flow<UiEvent>,
    onNavigation: (String) -> Unit
) {

    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->

            when (event) {
                is UiEvent.Success -> {

                }

                is UiEvent.Navigation -> {
                    onNavigation(event.route)
                }

                is UiEvent.NavigateUp -> {

                }
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(
        uiEvent = flow { },
        onNavigation = {}
    )
}