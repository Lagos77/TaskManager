package com.taskmanager.presentation.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.taskmanager.presentation.components.TopBar
import com.taskmanager.presentation.components.TopBarTitle

@Destination
@Composable
fun TasksScreen(
    currentScreen: TopBarTitle,
    taskId: Int = 0,
) {
    TasksScreenContent(currentScreen = currentScreen, taskId = taskId)
}

@Composable
private fun TasksScreenContent(
    currentScreen: TopBarTitle,
    taskId: Int,
) {
    Scaffold(
        topBar = { TopBar(currentScreen = currentScreen) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (taskId == 0) {
                CreateScreen()
            } else {
                EditScreen(taskId = taskId)
            }
        }
    }
}

@Composable
private fun CreateScreen() {
    Text("This is CreateScreen")
}

@Composable
private fun EditScreen( //TODO Should add task name as title
    taskId: Int
) {
    Text("This is EditScreen with task $taskId")
}


@Preview(showBackground = true)
@Composable
private fun CreateTaskScreenPreview() {
    TasksScreenContent(currentScreen = TopBarTitle.CREATE, taskId = 0)
}

@Preview(showBackground = true)
@Composable
private fun EditTaskScreenPreview() {
    TasksScreenContent(currentScreen = TopBarTitle.EDIT, taskId = 1)
}