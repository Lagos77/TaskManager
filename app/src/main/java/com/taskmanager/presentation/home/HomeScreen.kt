package com.taskmanager.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.taskmanager.R
import com.taskmanager.domain.TaskInfo
import com.taskmanager.presentation.components.TaskCard
import com.taskmanager.presentation.components.Toolbar
import com.taskmanager.presentation.components.TopBarTitle
import com.taskmanager.presentation.destinations.SettingsScreenDestination
import com.taskmanager.presentation.destinations.TasksFlowDestination
import com.taskmanager.ui.Spacing

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    HomeScreenContent(
        tasks = viewState.value.taskList,
        onDeleteTask = { viewModel.deleteSelectedTask(it) },
        navigateToCreateTaskScreen = { navigator.navigate(TasksFlowDestination(currentScreen = TopBarTitle.CREATE)) },
        navigateToEditTaskScreen = {
            navigator.navigate(
                TasksFlowDestination(
                    currentScreen = TopBarTitle.EDIT,
                    taskId = it
                )
            )
        },
        navigateToSettingsScreen = { navigator.navigate(SettingsScreenDestination) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    tasks: List<TaskInfo>,
    onDeleteTask: (TaskInfo) -> Unit,
    navigateToCreateTaskScreen: () -> Unit,
    navigateToEditTaskScreen: (Int) -> Unit,
    navigateToSettingsScreen: () -> Unit,
) {
    Scaffold(
        topBar = { Toolbar(currentScreen = TopBarTitle.HOME) },
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
                    label = { Text(stringResource(R.string.home_bottom_bar_create_task)) },
                    selected = false,
                    onClick = { navigateToCreateTaskScreen() }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            Icons.Default.Build, //TODO Change icon to moon/sun
                            contentDescription = stringResource(R.string.cd_toggle_theme)
                        )
                    },
                    label = { Text(stringResource(R.string.home_bottom_bar_theme)) },
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
            items(tasks.size, key = { it }) { index ->
                val task = tasks[index]
                TaskCard(
                    title = task.id.toString(), description = task.task,
                    onClick = { navigateToEditTaskScreen(task.id) },
                    onDelete = { onDeleteTask(task) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        tasks = emptyList(),
        onDeleteTask = {},
        navigateToCreateTaskScreen = {},
        navigateToEditTaskScreen = {},
        navigateToSettingsScreen = {},
    )
}