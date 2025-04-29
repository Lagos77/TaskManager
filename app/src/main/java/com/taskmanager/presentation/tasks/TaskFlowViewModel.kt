package com.taskmanager.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taskmanager.domain.TaskInfo
import com.taskmanager.domain.usecase.AddTaskUseCase
import com.taskmanager.domain.usecase.GetTaskUseCase
import com.taskmanager.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

private const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm"

@HiltViewModel
class TaskFlowViewModel @Inject constructor(
    private val addTask: AddTaskUseCase,
    private val getTask: GetTaskUseCase,
    private val updateTask: UpdateTaskUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(TaskFlowViewState())
    val viewState: MutableStateFlow<TaskFlowViewState> = _state

    private val formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)

    fun createTask(task: String) {
        val currentDateTime = LocalDateTime.now().format(formatter)
        val newTask = TaskInfo(
            task = task,
            dateTimeCreated = currentDateTime,
        )
        viewModelScope.launch {
            addTask.invoke(newTask)
        }
    }

    fun getSelectedTask(taskId: Int?) {
        taskId?.let {
            viewModelScope.launch {
                val task = getTask.invoke(it)
                _state.update { it.copy(currentTask = task) }
            }
        }
    }

    fun updateSelectedTask(task: TaskInfo) {
        viewModelScope.launch {
            updateTask.invoke(task)
        }
    }
}

data class TaskFlowViewState(
    val currentTask: TaskInfo? = null,
    val loading: Boolean = false,
    val taskCreated: Boolean = false,
)