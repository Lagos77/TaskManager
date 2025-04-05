package com.taskmanager.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
private fun HomeScreenContent(
    navigateToTaskScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        navigateToTaskScreen = {},
        navigateToSettingsScreen = {},
    )
}