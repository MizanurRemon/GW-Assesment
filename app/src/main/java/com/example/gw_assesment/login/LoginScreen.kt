package com.example.gw_assesment.login

import android.content.res.Configuration
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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R
import com.example.gw_assesment.components.CommonActionButton
import com.example.gw_assesment.components.CommonTextField
import com.example.gw_assesment.components.PasswordTextField
import com.example.gw_assesment.components.TextFieldTitleCompose
import com.example.gw_assesment.utils.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun LoginScreen(
    snackBarHostState: SnackbarHostState,
    uiEvent: Flow<UiEvent>,
    onEvent: (LoginEvent) -> Unit,
    onNavigation: (String) -> Unit,
    state: LoginState
) {

    LaunchedEffect(key1 = Unit) {
        uiEvent.collect {event->
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
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.displayLarge.toSpanStyle()
                            .copy(
                                color = Color.White, fontFamily = FontFamily(
                                    Font(R.font.comfortaa_bold, FontWeight.Bold)
                                )
                            )
                    ) {
                        append(stringResource(R.string.odoo))
                    }

                    append(" ")

                    withStyle(
                        style = MaterialTheme.typography.headlineSmall.toSpanStyle()
                            .copy(color = Color.White)
                    )
                    {
                        append(stringResource(R.string.mobile))
                    }
                },
                modifier = Modifier
                    .padding(vertical = 80.dp)
                    .align(alignment = Alignment.Center)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)

        ) {

            TextFieldTitleCompose(text = R.string.email)
            Spacer(modifier = Modifier.height(8.dp))

            CommonTextField(
                value = state.email,
                onValueChange = {
                    onEvent(LoginEvent.OnEmailEnter(it))
                },
                placeholder = "user@example.com",
                isTouched = true,
                isValid = true,
                onTouched = {

                },
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextFieldTitleCompose(text = R.string.password)

            Spacer(modifier = Modifier.height(8.dp))

            PasswordTextField(
                value = state.password,
                onValueChange = {
                    onEvent(LoginEvent.OnPasswordEnter(it))
                },
                placeholder = "********",
                isTouched = true,
                isValid = true,
                onTouched = {

                },
                shape = RoundedCornerShape(8.dp)
            )


            CommonActionButton(
                onClick = {
                    onEvent(LoginEvent.OnSubmitEvent)
                },
                text = R.string.login,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
            )

        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewLoginScreen() {
    LoginScreen(
        snackBarHostState = remember { SnackbarHostState() },
        uiEvent = flow {},
        onEvent = {},
        onNavigation = {},
        state = LoginState()
    )
}