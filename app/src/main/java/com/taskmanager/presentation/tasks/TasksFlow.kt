package com.taskmanager.presentation.tasks

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.taskmanager.presentation.components.Toolbar
import com.taskmanager.presentation.components.TopBarTitle
import com.taskmanager.ui.Spacing

@Destination
@Composable
fun TasksFlow(
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
    var taskText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(horizontal = Spacing.smallMedium),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = taskText,
            onValueChange = { taskText = it },
            modifier = Modifier
                .fillMaxSize(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black
            ),
        )
    }
}

@Composable
private fun EditScreen( //TODO Should add task name as title
    taskId: Int
) {
    var isEditing by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf("This is EditScreen with task $taskId") }

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
    TasksScreenContent(currentScreen = TopBarTitle.CREATE, taskId = 0)
}

@Preview(showBackground = true)
@Composable
private fun EditTaskScreenPreview() {
    TasksScreenContent(currentScreen = TopBarTitle.EDIT, taskId = 1)
}