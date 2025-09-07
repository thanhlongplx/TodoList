package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.MainActivity
import com.example.todolist.utils.SessionManager

class SplashActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        val sessionManager = SessionManager(this)
        Handler(Looper.getMainLooper()).postDelayed({
            val next = if (sessionManager.isLoggedIn) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
            startActivity(next)
            finish()
        }, 1500)
    }
}