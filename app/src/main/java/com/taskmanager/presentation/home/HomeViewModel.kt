package com.taskmanager.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanager.domain.TaskInfo
import com.taskmanager.domain.usecase.DeleteTaskUseCase
import com.taskmanager.domain.usecase.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllTasks: GetAllTasksUseCase,
    private val deleteTask: DeleteTaskUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState> = _state

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            getAllTasks.invoke().collect { tasks ->
                _state.update { it.copy(taskList = tasks) }
            }
        }
    }

    fun deleteSelectedTask(taskInfo: TaskInfo) {
        viewModelScope.launch {
            deleteTask.invoke(taskInfo)
        }
    }
}

data class HomeViewState(
    val taskList: List<TaskInfo> = emptyList()
)