package com.example.gw_assesment.components

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R

@Composable
fun CommonActionButton(
    @StringRes text: Int = R.string.login,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    bgColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = Color.White,
    shape : Shape = RoundedCornerShape(8.dp)
) {
    Box(
        modifier = modifier
            .background(color = bgColor, shape = shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            ) {
                onClick()
            }
    ) {
        Text(
            text = stringResource(text).uppercase(),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .padding(vertical = 15.dp),
            style = textStyle.copy(color = textColor, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
@Preview
fun PreviewCommonActionButton() {
    CommonActionButton(
        onClick = {}
    )
}