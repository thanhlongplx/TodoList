package com.example.todolist.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.dao.UserDao
import com.example.todolist.data.entity.User

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
    suspend fun register(username: String, password: String) {
        val user = User(username = username, password = password)
        userDao.insert(user)
    }

    suspend fun login(username: String, password: String): Boolean {
        return userDao.getUser(username, password) != null
    }

    suspend fun isUsernameExists(username: String): Boolean {
        return userDao.getUserByUsername(username) != null
    }
    suspend fun getUserByUsername(username: String): Boolean {
        return userDao.getUserByUsername(username) != null
    }


}