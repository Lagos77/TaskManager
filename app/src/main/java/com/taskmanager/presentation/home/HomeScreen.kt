package com.taskmanager.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.taskmanager.presentation.destinations.SettingsScreenDestination
import com.taskmanager.presentation.destinations.TasksScreenDestination

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    HomeScreenContent(
        navigateToTaskScreen = { navigator.navigate(TasksScreenDestination) },
        navigateToSettingsScreen = { navigator.navigate(SettingsScreenDestination) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    navigateToTaskScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Testing") },
                    modifier = Modifier.fillMaxWidth(),
                )
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            }
        },
        bottomBar = {

        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("This is OneScreen")

            Button(onClick = navigateToTaskScreen) {
                Text("Go to TaskScreen")
            }

            Button(onClick = navigateToSettingsScreen) {
                Text("Go to SettingsScreen")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        navigateToTaskScreen = {},
        navigateToSettingsScreen = {},
    )
}