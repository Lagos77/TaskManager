package com.taskmanager.domain.usecase

import com.taskmanager.domain.TasksInterface
import javax.inject.Inject

class DeleteAllTasksUseCase @Inject constructor(private val repository: TasksInterface) {
    suspend fun invoke() {
        repository.deleteAll()
    }
}