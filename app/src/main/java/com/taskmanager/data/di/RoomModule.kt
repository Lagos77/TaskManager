package com.taskmanager.data.di

import android.content.Context
import androidx.room.Room
import com.taskmanager.data.local.AppDatabase
import com.taskmanager.data.local.TaskDao
import com.taskmanager.data.repository.TasksRepository
import com.taskmanager.domain.TasksInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "task_manager_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: AppDatabase): TaskDao {
        return database.taskDao()
    }

    @Provides
    fun provideTasksInterface(tasksRepository: TasksRepository): TasksInterface = tasksRepository
}