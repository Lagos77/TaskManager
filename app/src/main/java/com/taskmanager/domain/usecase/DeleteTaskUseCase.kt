package com.taskmanager.domain.usecase

import com.taskmanager.domain.TaskInfo
import com.taskmanager.domain.TasksInterface
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: TasksInterface) {
    suspend fun invoke(task: TaskInfo) {
        repository.deleteTask(task)
    }
}