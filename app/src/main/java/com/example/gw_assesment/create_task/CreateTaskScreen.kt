package com.example.gw_assesment.create_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gw_assesment.R
import com.example.gw_assesment.components.CustomToolbar
import com.example.gw_assesment.utils.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun CreateTaskScreen(
    onBack: () -> Unit,
    uiEvent: Flow<UiEvent>,
    state: CreateTaskState,
    onEvent: (CreateTaskEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CustomToolbar(
            onClick = {
                onBack()
            },
            text = " ${stringResource(R.string.create_task)}"
        )
    }
}

@Composable
@Preview
fun PreviewCreateTaskScreen() {
    CreateTaskScreen(
        onBack = {},
        state = CreateTaskState(),
        onEvent = {},
        uiEvent = flow {}
    )
}