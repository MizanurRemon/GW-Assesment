package com.example.gw_assesment.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R
import com.example.gw_assesment.components.CustomToolbar
import com.example.gw_assesment.utils.Route
import com.example.gw_assesment.utils.capitalizeFirstChar

@Composable
fun HomeScreen(
    state: HomeState,
    onNavigate:(String)-> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CustomToolbar(
                onClick = {},
                text = "${stringResource(R.string.odoo).capitalizeFirstChar()} ${stringResource(R.string.mobile)}",
                trailingIcon = R.drawable.ic_more_vert
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 50.dp, end = 20.dp),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                onNavigate(Route.CREATE)
            }
        ) {
            Image(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Color.White)
            )
        }
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(
        state = HomeState(),
        onNavigate = {}
    )
}