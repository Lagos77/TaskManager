package com.taskmanager.presentation.components

import android.os.Parcelable
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
import androidx.compose.ui.res.stringResource
import com.taskmanager.R
import com.taskmanager.ui.Spacing.large
import kotlinx.parcelize.Parcelize


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    currentScreen: TopBarTitle = TopBarTitle.HOME,
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
                        text = when (currentScreen) {
                            TopBarTitle.HOME -> stringResource(R.string.app_name)
                            TopBarTitle.SETTINGS -> stringResource(R.string.home_bottom_bar_settings)
                            TopBarTitle.CREATE -> stringResource(R.string.create_screen_title)
                            TopBarTitle.EDIT -> stringResource(R.string.edit_screen_title)
                        }
                    )
                    Column(
                        modifier = Modifier.padding(end = large)
                    ) {
                        when (currentScreen) {
                            TopBarTitle.HOME -> {
                                HomeTopBarContent(
                                    total = totalTasks,
                                    alarms = totalAlarms,
                                )
                            }
                            TopBarTitle.SETTINGS -> {}
                            TopBarTitle.CREATE -> {}
                            TopBarTitle.EDIT -> {}
                        }

                    }
                }
            }
        )
    }
}

@Composable
private fun HomeTopBarContent(
    total: Int,
    alarms: Int,
) {
    Text(
        text = "Total tasks: $total",  //TODO Make it dynamic
        style = MaterialTheme.typography.labelLarge,
    )
    Text(
        text = "Alarms: $alarms",  //TODO Make it dynamic
        style = MaterialTheme.typography.labelLarge,
    )
}

@Parcelize
enum class TopBarTitle : Parcelable { HOME, SETTINGS, CREATE, EDIT, }