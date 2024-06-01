package com.example.what_to_do.data.source.local

import com.example.what_to_do.data.Task
import com.example.what_to_do.data.source.TaskDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.PrivateKey

class LocalDataSource(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): TaskDataSource {
    override suspend fun saveTask(task: Task) {
        withContext(ioDispatcher){
            taskDao.insert(task)
        }
    }
}