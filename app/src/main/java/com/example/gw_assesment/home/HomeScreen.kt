package com.example.gw_assesment.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R
import com.example.gw_assesment.components.CustomToolbar
import com.example.gw_assesment.model.TaskResponse
import com.example.gw_assesment.utils.Route
import com.example.gw_assesment.utils.StatusType
import com.example.gw_assesment.utils.capitalizeFirstChar

@Composable
fun HomeScreen(
    state: HomeState,
    onNavigate: (String) -> Unit
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(top = 20.dp)
            ) {
                LazyColumn(
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.taskList) { item ->
                        TaskItem(item)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
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
fun TaskItem(item: TaskResponse) {
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

            Text(
                text = "${stringResource(R.string.due)}: ${item.dueDate}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray
                )
            )
        }
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(
        state = HomeState(
            taskList = TASK_LIST
        ),
        onNavigate = {}
    )
}