package com.example.what_to_do.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.what_to_do.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    companion object{
        private var database: ToDoDatabase? = null
        fun getInstance(context: Context): ToDoDatabase {
            if (database == null){
                database = Room.databaseBuilder(
                    context,
                    ToDoDatabase::class.java,
                    "task_database"
                ).build()
                return database as ToDoDatabase
            }
            return database as ToDoDatabase
        }
    }
}
