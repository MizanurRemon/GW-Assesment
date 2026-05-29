package com.example.gw_assesment.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.gw_assesment.R
import com.example.gw_assesment.components.CustomToolbar
import com.example.gw_assesment.model.TaskResponse
import com.example.gw_assesment.utils.Route
import com.example.gw_assesment.utils.StatusType
import com.example.gw_assesment.utils.capitalizeFirstChar

@Composable
fun HomeScreen(
    state: HomeState,
    onNavigate: (String) -> Unit,
    onItemClick: () -> Unit,
    onEvent: (HomeEvent) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                onEvent(HomeEvent.OnRefresh)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(top = 20.dp)
            ) {

                Text(
                    text = "${stringResource(R.string.welcome)}, ${state.userName}!",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    state = rememberLazyListState()
                ) {
                    if (!state.isLoading) {
                        items(state.taskList) { item ->
                            TaskItem(
                                item = item,
                                onClick = { onItemClick() }
                            )
                        }
                    } else {
                        items(7) {
                            TaskItemSkeleton()
                        }
                    }

                }


            }
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
private fun TaskItem(item: TaskResponse, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(1.dp),
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = item.status,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = StatusType.fromType(item.status).statusColor,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .background(
                                color = StatusType.fromType(item.status).statusBackgroundColor,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(3.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${stringResource(R.string.due)}: ${item.dueDate}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun TaskItemSkeleton() {
    Column(modifier = Modifier.fillMaxWidth()) {

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .height(18.dp)
                            .width(120.dp)
                            .background(
                                Color.LightGray.copy(alpha = 0.4f),
                                RoundedCornerShape(4.dp)
                            )
                    )

                    Spacer(modifier = Modifier.weight(1f))


                    Box(
                        modifier = Modifier
                            .height(18.dp)
                            .width(70.dp)
                            .background(
                                Color.LightGray.copy(alpha = 0.4f),
                                RoundedCornerShape(6.dp)
                            )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .height(14.dp)
                        .width(160.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(
        state = HomeState(
            taskList = TASK_LIST
        ),
        onNavigate = {},
        onItemClick = {},
        onEvent = {}
    )
}