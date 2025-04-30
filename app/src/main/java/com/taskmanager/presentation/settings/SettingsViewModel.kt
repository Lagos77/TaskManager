package com.taskmanager.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanager.domain.usecase.DeleteAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val deleteAll: DeleteAllTasksUseCase) :
    ViewModel() {

    fun delete() {
        viewModelScope.launch {
            deleteAll.invoke()
        }
    }
}