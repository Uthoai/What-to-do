package com.example.what_to_do.data.source

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.what_to_do.data.Task
import com.example.what_to_do.data.source.local.LocalDataSource
import com.example.what_to_do.data.source.local.ToDoDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultTaskRepository private constructor(application: Application) {

    private val localDataSource: LocalDataSource
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    init {
        localDataSource =
            LocalDataSource(ToDoDatabase.getInstance(application.applicationContext).taskDao())
    }

    companion object{
        private var repository: DefaultTaskRepository? = null
        fun getInstance(application: Application): DefaultTaskRepository{
            if (repository == null){
                repository = DefaultTaskRepository(application)
                return repository as DefaultTaskRepository
            }
            return repository as DefaultTaskRepository
        }
    }

    suspend fun saveTask(task: Task){
        withContext(ioDispatcher){
            localDataSource.saveTask(task)
        }
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return localDataSource.getAllTask()
    }

    fun getTaskById(id: Int) = localDataSource.getTaskById(id)

    suspend fun updateTask(task: Task){
        withContext(ioDispatcher){
            localDataSource.updateTask(task)
        }
    }

}