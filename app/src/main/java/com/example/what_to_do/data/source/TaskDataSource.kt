package com.example.what_to_do.data.source

import com.example.what_to_do.data.Task

interface TaskDataSource {
    suspend fun saveTask(task: Task)
}