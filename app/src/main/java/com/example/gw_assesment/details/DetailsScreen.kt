package com.example.gw_assesment.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R
import com.example.gw_assesment.components.CommonActionButton
import com.example.gw_assesment.components.CustomSpinner
import com.example.gw_assesment.components.CustomToolbar
import com.example.gw_assesment.components.LoadingDialog
import com.example.gw_assesment.utils.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun DetailsScreen(
    snackBarHostState: SnackbarHostState,
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit,
    uiEvent: Flow<UiEvent>,
    onBack: () -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        uiEvent.collect {event->
            when (event) {
                is UiEvent.Success -> {
                    onBack()
                }

                is UiEvent.Navigation -> {

                }

                is UiEvent.NavigateUp -> {

                }

                is UiEvent.ShowSnackBar-> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context = context),
                        duration = SnackbarDuration.Short,
                    )
                }
            }
        }
    }

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
                text = " ${stringResource(R.string.update_task)}"
            )

            Column(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
            ) {

                Text(text = state.taskResponse.title, style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = state.taskResponse.dueDate, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(30.dp))

                Text(text = stringResource(R.string.status), style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(10.dp))

                CustomSpinner(
                    items = state.statusList,
                    selectedItem = state.selectedItem,
                    onItemSelected = {
                        onEvent(DetailsEvent.OnStatusSelection(it))
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                CommonActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(DetailsEvent.OnUpdateClick)
                    },
                    text = R.string.update_task
                )
            }
        }
    }

    if(state.isLoading){
        LoadingDialog {  }
    }
}

@Composable
@Preview
fun PreviewDetailsScreen() {
    DetailsScreen(
        state = DetailsState(),
        uiEvent = flow {},
        onEvent = {},
        onBack = {},
        snackBarHostState = remember { SnackbarHostState() }
    )
}