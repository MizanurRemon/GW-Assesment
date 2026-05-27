package com.example.gw_assesment.create_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R
import com.example.gw_assesment.components.CommonActionButton
import com.example.gw_assesment.components.CommonTextField
import com.example.gw_assesment.components.CustomDatePicker
import com.example.gw_assesment.components.CustomToolbar
import com.example.gw_assesment.components.TextFieldTitleCompose
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

                TextFieldTitleCompose(
                    text = R.string.title
                )
                Spacer(modifier = Modifier.height(8.dp))
                CommonTextField(
                    value = state.title,
                    onValueChange = {
                        onEvent(CreateTaskEvent.OnTitleEnter(it))
                    },
                    placeholder = stringResource(R.string.enter_title),
                    isTouched = true,
                    isValid = true,
                    onTouched = {

                    },
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextFieldTitleCompose(
                    text = R.string.description
                )
                Spacer(modifier = Modifier.height(8.dp))
                CommonTextField(
                    value = state.description,
                    onValueChange = {
                        onEvent(CreateTaskEvent.OnDescriptionEnter(it))
                    },
                    placeholder = stringResource(R.string.enter_task_description),
                    isTouched = true,
                    isValid = true,
                    onTouched = {

                    },
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextFieldTitleCompose(
                    text = R.string.due_date
                )
                Spacer(modifier = Modifier.height(8.dp))
                CommonTextField(
                    readOnly = true,
                    value = state.dueDate,
                    onValueChange = {

                    },
                    placeholder = "",
                    isTouched = true,
                    isValid = true,
                    onTouched = {
                        onEvent(CreateTaskEvent.OnDateSelectionDialog(true))
                    },
                    shape = RoundedCornerShape(8.dp),
                    trailingIcon = painterResource(R.drawable.ic_calender)
                )

                Spacer(modifier = Modifier.weight(1f))

                CommonActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    },
                    text = R.string.create_task
                )
            }

        }


        CustomDatePicker(
            selectedDate = state.dueDate,
            showDatePicker = state.isDialogOpen,
            onShowDatePickerChange = {
                onEvent(CreateTaskEvent.OnDateSelectionDialog(it))
            },
            onDateSelected = {
                //selectedDate = it
                onEvent(CreateTaskEvent.OnDateSelection(it))
            }
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