package com.example.what_to_do.view.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.what_to_do.data.Task
import com.example.what_to_do.data.source.DefaultTaskRepository

class TaskViewModel(application: Application): AndroidViewModel(application) {
    val repository : DefaultTaskRepository = DefaultTaskRepository.getInstance(application)

    /*fun getAllTask(): LiveData<List<Task>>{
        return repository.getAllTasks()
    }*/

    var allNote: LiveData<List<Task>> = repository.getAllTasks()

}