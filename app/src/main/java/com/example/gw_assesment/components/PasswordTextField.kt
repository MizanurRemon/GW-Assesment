package com.example.gw_assesment.components

import android.content.res.Configuration
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isTouched: Boolean,
    isValid: Boolean,
    onTouched: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(id = R.string.password),
    keyboardController: SoftwareKeyboardController? = null,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    cursorColor: Color? = Color.Black
) {
    val scheme = MaterialTheme.colorScheme
    var showPassword by remember { mutableStateOf(false) }

    val effectiveCursor = cursorColor ?: scheme.onPrimary
    val isError = isTouched && !isValid
    val borderColor = when {
        isError -> scheme.error
        isTouched -> scheme.onPrimary
        else -> scheme.outline.copy(alpha = 0.35f)
    }



    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .border(
                width = 1.dp,
                shape = shape,
                color = borderColor
            )
            .background(scheme.surfaceColorAtElevation(1.dp), shape = shape)
            .padding(horizontal = 10.dp)
            .onFocusEvent { event -> if (event.isFocused) onTouched() }
            .pointerInteropFilter {
                if (it.action == MotionEvent.ACTION_DOWN) onTouched()
                false
            }
    ) {

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = scheme.onSurface,
                textAlign = TextAlign.Start
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                defaultKeyboardAction(ImeAction.Done)
            }),
            cursorBrush = SolidColor(effectiveCursor),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 15.dp),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = scheme.onSurfaceVariant.copy(alpha = 0.7f),
                            textAlign = TextAlign.Start
                        )
                    )
                }
                innerTextField()
            }
        )

        Image(
            painter = painterResource(
                id = if (showPassword) {
                    R.drawable.ic_eye_close
                } else {
                    R.drawable.ic_eye_open
                }
            ),
            contentDescription = null,
            //tint = scheme.onSurfaceVariant,
            modifier = Modifier
                .clickable { showPassword = !showPassword }
                .size(16.dp)
        )
    }
}

@Preview(name = "Password – Light", showBackground = true)
@Preview(
    name = "Password – Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewPasswordTextField() {
    var password by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    PasswordTextField(
        value = password,
        onValueChange = { password = it },
        isTouched = password.isNotEmpty(),
        isValid = password.length >= 6,
        onTouched = { /* no-op */ },
        keyboardController = keyboardController
    )
}