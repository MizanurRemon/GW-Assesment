package com.example.gw_assesment.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R
import com.example.gw_assesment.components.CommonActionButton
import com.example.gw_assesment.components.CustomToolbar
import com.example.gw_assesment.utils.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun DetailsScreen(
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit,
    uiEvent: Flow<UiEvent>,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomToolbar(
                onClick = {
                    onBack()
                },
                text = " ${stringResource(R.string.create_task)}"
            )

            Column(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                CommonActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    },
                    text = R.string.update_task
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewDetailsScreen() {
    DetailsScreen(
        state = DetailsState(),
        uiEvent = flow {},
        onEvent = {},
        onBack = {}
    )
}