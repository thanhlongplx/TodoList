package com.example.todolist.model

data class User(
    val id: Int = 0,          // ID duy nhất, tự động tăng
    val username: String,     // Tên đăng nhập
    val password: String      // Mật khẩu (sẽ được mã hóa)
)