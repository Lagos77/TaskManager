package com.taskmanager.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.taskmanager.ui.Spacing.large

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    totalTasks: Int = 0,
    totalAlarms: Int = 0,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "TaskManager", //TODO Make it dynamic
                    )
                    Column(
                        modifier = Modifier.padding(end = large)
                    ) {
                        Text(
                            text = "Total tasks: $totalTasks",  //TODO Make it dynamic
                            style = MaterialTheme.typography.labelLarge,
                        )
                        Text(
                            text = "Alarms: $totalAlarms",  //TODO Make it dynamic
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
            }
        )
    }
}