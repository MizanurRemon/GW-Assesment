package com.example.gw_assesment

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.components.CommonActionButton
import com.example.gw_assesment.components.CommonTextField
import com.example.gw_assesment.components.CustomToolbar
import com.example.gw_assesment.components.TextFieldTitleCompose

@Composable
fun UpdateAccountScreen(
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
                text = " ${stringResource(R.string.update_account)}"
            )

            Column(
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
            ) {

                TextFieldTitleCompose(
                    text = R.string.name
                )
                Spacer(modifier = Modifier.height(8.dp))
                CommonTextField(
                    value ="",
                    onValueChange = {

                    },
                    placeholder = stringResource(R.string.enter_name),
                    isTouched = true,
                    isValid = true,
                    onTouched = {

                    },
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                CommonActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                    },
                    text = R.string.save_changes
                )

            }
        }
    }
}


@Composable
@Preview
fun PreviewUpdateAccountScreen() {
    UpdateAccountScreen(
        onBack = {}
    )
}