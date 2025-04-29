package com.taskmanager.data.repository

import com.taskmanager.data.local.TaskDao
import com.taskmanager.data.mapToData
import com.taskmanager.data.mapToDomain
import com.taskmanager.domain.TaskInfo
import com.taskmanager.domain.TasksInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TasksRepository @Inject constructor(private val dao: TaskDao) : TasksInterface {
    override suspend fun createTask(task: TaskInfo) {
        dao.insert(task.mapToData())
    }

    override suspend fun getTask(id: Int): TaskInfo {
        return dao.get(id).mapToDomain()
    }

    override suspend fun updateTask(task: TaskInfo) {
        dao.update(task.mapToData())
    }

    override fun getAllTasks(): Flow<List<TaskInfo>> {
        return dao.getAll().map { list ->
            list.map { it.mapToDomain() }
        }
    }

    override suspend fun deleteTask(task: TaskInfo) {
        dao.delete(task.mapToData())
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}