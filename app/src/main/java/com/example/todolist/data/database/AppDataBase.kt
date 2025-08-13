package com.example.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.data.dao.TodoDao
import com.example.todolist.data.entity.Todo
import com.example.todolist.data.entity.User
import com.example.todolist.data.dao.UserDao // Tạo file này sau

@Database(
    entities = [Todo::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun userDao(): UserDao
}