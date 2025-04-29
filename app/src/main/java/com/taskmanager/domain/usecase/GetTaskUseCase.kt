package com.taskmanager.domain.usecase

import com.taskmanager.domain.TaskInfo
import com.taskmanager.domain.TasksInterface
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(private val repository: TasksInterface) {
    suspend fun invoke(id: Int): TaskInfo {
        return repository.getTask(id)
    }
}