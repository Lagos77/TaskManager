package com.taskmanager.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val task: String = "",
    val isDone: Boolean = false,
    val dateTimeCreated: String = "",
    val dateTimeEdited: String = "",
)