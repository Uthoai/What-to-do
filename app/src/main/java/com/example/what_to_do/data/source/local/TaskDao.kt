package com.example.what_to_do.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.what_to_do.data.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)
    @Query("SELECT * FROM task_table")
    fun getAllTask(): LiveData<List<Task>>
    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getTaskById(id: Int): LiveData<Task>
}