package com.taskmanager.domain

data class TaskInfo(
    val id: Int = 0,
    val task: String = "",
    val isDone: Boolean = false,
    val dateTimeCreated: String = "",
    val dateTimeEdited: String = "",
)