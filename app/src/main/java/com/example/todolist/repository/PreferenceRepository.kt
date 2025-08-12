package com.example.todolist.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


data class Account(val username: String, val password: String)

class PreferenceRepository(context: Context) {

    companion object {
        private const val PREF_NAME = "todo_app_pref"
        private const val KEY_ACCOUNTS = "accounts"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val gson = Gson()

    // Lấy danh sách tài khoản từ SharedPreferences
    private fun getAccounts(): MutableList<Account> {
        val json = sharedPreferences.getString(KEY_ACCOUNTS, "[]") // mặc định là mảng rỗng
        val type = object : TypeToken<MutableList<Account>>() {}.type
        return gson.fromJson(json, type)
    }

    // Lưu danh sách tài khoản
    private fun saveAccounts(accounts: List<Account>) {
        sharedPreferences.edit()
            .putString(KEY_ACCOUNTS, gson.toJson(accounts))
            .apply()
    }

    // Đăng ký tài khoản mới
    fun register(username: String, password: String): Boolean {
        val accounts = getAccounts()

        // Kiểm tra nếu username đã tồn tại
        if (accounts.any { it.username == username }) {
            return false // đăng ký thất bại vì trùng tên
        }

        accounts.add(Account(username, password))
        saveAccounts(accounts)
        return true
    }

    // Đăng nhập
    fun login(username: String, password: String): Boolean {
        val accounts = getAccounts()
        return accounts.any { it.username == username && it.password == password }
    }

    // Kiểm tra đã có tài khoản nào chưa
    fun isUserRegistered(): Boolean {
        return getAccounts().isNotEmpty()
    }
}