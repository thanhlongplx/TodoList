package com.example.todolist.model

data class Todo(
    val id: Int = 0,          // ID duy nhất cho mỗi công việc
    val title: String,        // Tiêu đề công việc
    val description: String? = null, // Mô tả (có thể null)
    val isCompleted: Boolean = false // Trạng thái hoàn thành
)