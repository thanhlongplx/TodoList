package com.example.todolist.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.dao.TodoDao
import com.example.todolist.data.entity.Todo
import com.example.todolist.data.entity.User

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(title: String, description: String) {
        val todo = Todo(title = title, description = description)
        todoDao.insert(todo)
    }

    suspend fun update(todo: Todo) {
        todoDao.update(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }


}
