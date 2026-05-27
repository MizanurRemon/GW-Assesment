package com.example.gw_assesment.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun TextFieldTitleCompose(
    @StringRes text: Int
){
    Text(text = stringResource(text), style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray))

}