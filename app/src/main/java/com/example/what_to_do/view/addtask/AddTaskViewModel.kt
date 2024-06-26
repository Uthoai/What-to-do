package com.example.what_to_do.view.addtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.what_to_do.R
import com.example.what_to_do.data.Task
import com.example.what_to_do.data.source.DefaultTaskRepository
import com.example.what_to_do.utils.toTrimString
import kotlinx.coroutines.launch

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val btnText = MutableLiveData<String>()
    val repository: DefaultTaskRepository = DefaultTaskRepository.getInstance(application)

    private val _snackbarMsg = MutableLiveData<Int>()
    val snackbarMsg get() = _snackbarMsg

    private val titleLength = 3
    private val maxTitleLength = 20
    private var noTaskID = 0
    private var currentTaskID = noTaskID

    /*init {
        btnText.postValue("Save")
    }*/

    fun getTaskById(id: Int): LiveData<Task>? {
        currentTaskID = id
        changeBtnName()
        return repository.getTaskById(id)
    }

    private fun changeBtnName() {
        if (currentTaskID != noTaskID) {
            btnText.postValue("Update")
        } else {
            btnText.postValue("Save")
        }
    }

    fun saveTask() {
        val currentTitle = title.value
        val currentDescription = description.value
        val task = Task(
            id = currentTaskID,
            title = currentTitle.toTrimString(),
            description = currentDescription.toTrimString()
        )

        if (!isValidTask(currentTitle, currentDescription)) {
            return
        }

        if (currentTaskID == noTaskID) {
            createTask(task)
        } else {
            updateTask(task)
        }

    }

    private fun isValidTask(currentTitle: String?, currentDescription: String?): Boolean {
        if (currentTitle.isNullOrEmpty() || currentDescription.isNullOrEmpty()) {
            _snackbarMsg.postValue(R.string.empty_task_message)
            return false
        }
        if (currentTitle.length < titleLength) {
            _snackbarMsg.postValue(R.string.titleLenght)
        }
        if (currentTitle.length > maxTitleLength) {
            _snackbarMsg.postValue(R.string.title_length_max_20)
            return false
        }
        return true
    }

    private fun createTask(task: Task) {
        viewModelScope.launch {
            repository.saveTask(task)
        }
    }

    private fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

}