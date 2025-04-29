package com.taskmanager.domain.usecase

import com.taskmanager.domain.TaskInfo
import com.taskmanager.domain.TasksInterface
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val repository: TasksInterface) {
    suspend fun invoke(task: TaskInfo) {
        repository.createTask(task)
    }
}