package com.example.what_to_do.data.source

import androidx.lifecycle.LiveData
import com.example.what_to_do.data.Task

interface TaskDataSource {
    suspend fun saveTask(task: Task)

    suspend fun updateTask(task: Task)

    fun getAllTask(): LiveData<List<Task>>

    fun getTaskById(id: Int): LiveData<Task>?
}