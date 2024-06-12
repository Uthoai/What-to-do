package com.example.what_to_do.view.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.what_to_do.data.Task
import com.example.what_to_do.data.source.DefaultTaskRepository

class TaskViewModel(application: Application): AndroidViewModel(application) {
    private val repository : DefaultTaskRepository = DefaultTaskRepository.getInstance(application)

    var getAllTask: LiveData<List<Task>> = repository.getAllTasks()

}