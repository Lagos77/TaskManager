package com.taskmanager.domain.usecase

import com.taskmanager.domain.TaskInfo
import com.taskmanager.domain.TasksInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(private val repository: TasksInterface) {
    fun invoke(): Flow<List<TaskInfo>> {
        return repository.getAllTasks()
    }
}