package com.taskmanager.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.taskmanager.R
import com.taskmanager.presentation.components.TopBar
import com.taskmanager.presentation.destinations.SettingsScreenDestination
import com.taskmanager.presentation.destinations.TasksScreenDestination
import com.taskmanager.ui.Spacing
import com.taskmanager.ui.theme.ThemeDefaults

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
        topBar = { TopBar() },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.onSurface
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = stringResource(R.string.cd_settings)
                        )
                    },
                    label = { Text(stringResource(R.string.cd_settings)) },
                    selected = false,
                    onClick = { navigateToSettingsScreen() }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Create,
                            contentDescription = stringResource(R.string.cd_create_task)
                        )
                    },
                    label = { Text(stringResource(R.string.home_create_task)) },
                    selected = false,
                    onClick = { navigateToTaskScreen() }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Build, //TODO Change icon to moon/sun
                            contentDescription = stringResource(R.string.cd_toggle_theme)
                        )
                    },
                    label = { Text(stringResource(R.string.home_theme)) },
                    selected = false,
                    onClick = {} //TODO
                )
            }
        }
    )
    { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                vertical = Spacing.medium,
                horizontal = Spacing.smallMedium
            ),
            verticalArrangement = Arrangement.spacedBy(Spacing.regular),
        ) {
            items(10) { index ->
                TaskCard(title = "Task $index", description = "This a description for $index")
            }
        }
    }
}

@Composable
private fun TaskCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Spacing.pico),
        shape = MaterialTheme.shapes.small,
        colors = ThemeDefaults.defaultCardColors(),
    ) {
        Column(modifier = Modifier.padding(Spacing.mediumPlus)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(Spacing.micro))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall
            )
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