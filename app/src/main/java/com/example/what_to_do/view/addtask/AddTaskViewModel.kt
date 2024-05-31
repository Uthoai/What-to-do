package com.example.what_to_do.view.addtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.what_to_do.data.Task
import com.example.what_to_do.data.source.DefaultTaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application): AndroidViewModel(application) {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val repository : DefaultTaskRepository = DefaultTaskRepository.getInstance(application)
    fun saveTask() {
        val currentTitle = title.value
        val currentDescription = description.value
        val task = Task(title = currentTitle.toString(), description = currentDescription.toString())

        createTask(task)

    }

    private fun createTask(task: Task) {
        viewModelScope.launch {
            repository.saveTask(task)
        }
    }

}