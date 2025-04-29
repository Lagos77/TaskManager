package com.taskmanager.domain

import kotlinx.coroutines.flow.Flow

interface TasksInterface {
    suspend fun createTask(task: TaskInfo)
    suspend fun getTask(id: Int): TaskInfo
    suspend fun updateTask(task: TaskInfo)
    fun getAllTasks(): Flow<List<TaskInfo>>
    suspend fun deleteTask(task: TaskInfo)
    suspend fun deleteAll()
}