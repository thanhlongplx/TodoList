package com.example.todolist.repository

import android.content.Context
import android.content.SharedPreferences

class PreferenceRepository(context: Context) {

    companion object {
        private const val PREF_NAME = "todo_app_pref"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun register(username: String, password: String) {
        sharedPreferences.edit()
            .putString(KEY_USERNAME, username)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    fun login(username: String, password: String): Boolean {
        val savedUsername = sharedPreferences.getString(KEY_USERNAME, null)
        val savedPassword = sharedPreferences.getString(KEY_PASSWORD, null)
        return username == savedUsername && password == savedPassword
    }

    fun isUserRegistered(): Boolean {
        return sharedPreferences.contains(KEY_USERNAME)
    }
}
