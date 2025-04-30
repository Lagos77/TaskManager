package com.taskmanager.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.taskmanager.presentation.components.Toolbar
import com.taskmanager.presentation.components.TopBarTitle

@Destination
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    SettingsScreenContent(
        onClearAllTasks = { viewModel.delete() }
    )
}

@Composable
private fun SettingsScreenContent(
    onClearAllTasks: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { Toolbar(currentScreen = TopBarTitle.SETTINGS) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {

            Text(
                text = "Appearance",
                style = MaterialTheme.typography.titleMedium
            )
            SettingSwitchItem(
                title = "Dark mode",
                checked = true,
                onCheckedChange = {}
            )

            Text(
                text = "Notifications",
                style = MaterialTheme.typography.titleMedium
            )
            SettingSwitchItem(
                title = "Enable notifications",
                checked = false,
                onCheckedChange = {}
            )

            Text(
                text = "Data",
                style = MaterialTheme.typography.titleMedium
            )
            SettingActionItem(
                title = "Clear completed tasks",
                onClick = { showDialog = true }
            )

            Text(
                text = "About",
                style = MaterialTheme.typography.titleMedium
            )
            SettingInfoItem(
                title = "App version",
                value = "1.0.0"
            )

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = {
                            onClearAllTasks()
                            showDialog = false
                        }) { Text("Yes") }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDialog = false
                        }) {
                            Text("Cancel")
                        }
                    },
                    title = {
                        Text("Confirm Clear")
                    },
                    text = {
                        Text("Are you sure you want to clear all completed tasks?")
                    },
                    properties = DialogProperties()
                )
            }
        }
    }
}

@Composable
private fun SettingSwitchItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
private fun SettingActionItem(
    title: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = title)
    }
}


@Composable
private fun SettingInfoItem(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}


@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreenContent(
        onClearAllTasks = {}
    )
}