package com.example.todolist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.data.entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getAllTodos(): LiveData<List<Todo>>
}
