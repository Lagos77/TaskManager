package com.taskmanager.data

import com.taskmanager.data.local.Task
import com.taskmanager.domain.TaskInfo

fun TaskInfo.mapToData(): Task {
    return Task(this.id, this.task, this.isDone, this.dateTimeCreated, this.dateTimeEdited)
}

fun Task.mapToDomain(): TaskInfo {
    return TaskInfo(this.id, this.task, this.isDone, this.dateTimeCreated, this.dateTimeEdited)
}