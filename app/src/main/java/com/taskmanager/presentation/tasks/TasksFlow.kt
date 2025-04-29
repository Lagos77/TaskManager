package com.taskmanager.presentation.tasks

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.taskmanager.domain.TaskInfo
import com.taskmanager.presentation.components.Toolbar
import com.taskmanager.presentation.components.TopBarTitle
import com.taskmanager.ui.Spacing

@Destination
@Composable
fun TasksFlow(
    destinationsNavigator: DestinationsNavigator,
    viewModel: TaskFlowViewModel = hiltViewModel(),
    currentScreen: TopBarTitle,
    taskId: Int? = null,
) {
    val viewState = viewModel.viewState.collectAsState()

    viewModel.getSelectedTask(taskId)

    TasksScreenContent(
        currentScreen = currentScreen,
        taskId = taskId,
        createTask = { viewModel.createTask(it) },
        editTask = viewState.value.currentTask,
        updateTask = { viewModel.updateSelectedTask(it) },
        popBackStack = { destinationsNavigator.popBackStack() },
    )
}

@Composable
private fun TasksScreenContent(
    currentScreen: TopBarTitle,
    taskId: Int? = null,
    createTask: (String) -> Unit,
    editTask: TaskInfo?,
    updateTask: (TaskInfo) -> Unit,
    popBackStack: () -> Unit
) {
    Scaffold(
        topBar = { Toolbar(currentScreen = currentScreen) },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Spacing.extraLarge)
                    .background(color = MaterialTheme.colorScheme.onSurface),
                horizontalArrangement = Arrangement.Center
            ) {
                //TODO Add vector drawable for undo
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            if (taskId == null) {
                CreateScreen(
                    createTask = createTask,
                    popBackStack = popBackStack,
                )
            } else {
                if (editTask != null) {
                    EditScreen(
                        currentTask = editTask,
                        updateTask = updateTask,
                        popBackStack = popBackStack,
                    )
                }
            }
        }
    }
}

@Composable
private fun CreateScreen(
    createTask: (String) -> Unit,
    popBackStack: () -> Unit,
) {
    var taskText by remember { mutableStateOf("") }

    BackHandler {
        if (taskText.isNotBlank()) {
            createTask(taskText)
        }
        popBackStack()
    }

    Column(
        modifier = Modifier.padding(horizontal = Spacing.smallMedium),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = taskText,
            onValueChange = { taskText = it },
            modifier = Modifier.fillMaxSize(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black
            ),
        )
    }
}

@Composable
private fun EditScreen(
    currentTask: TaskInfo,
    updateTask: (TaskInfo) -> Unit,
    popBackStack: () -> Unit,
) {
    var isEditing by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf(currentTask.task) }

    BackHandler {
        if (isEditing) {
            isEditing = false
        } else if (taskName != currentTask.task) {
            val updatedTask = currentTask.copy(task = taskName)
            updateTask(updatedTask)
            popBackStack()
        } else {
            popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = Spacing.smallMedium)
            .clickable { isEditing = true },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (isEditing) {
            TextField(
                value = taskName,
                onValueChange = { taskName = it },
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black
                ),
            )
        } else {
            Text(text = taskName)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CreateTaskScreenPreview() {
    TasksScreenContent(
        currentScreen = TopBarTitle.CREATE, taskId = 0,
        createTask = {},
        popBackStack = {},
        editTask = null,
        updateTask = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun EditTaskScreenPreview() {
    TasksScreenContent(
        currentScreen = TopBarTitle.EDIT, taskId = 1,
        createTask = {},
        popBackStack = {},
        editTask = null,
        updateTask = {},
    )
}