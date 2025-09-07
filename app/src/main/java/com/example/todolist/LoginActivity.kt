package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todolist.data.database.DatabaseProvider
import com.example.todolist.data.repository.UserRepository
import com.example.todolist.MainActivity
import com.example.todolist.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonGoRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputUsername = findViewById(R.id.input_username)
        inputPassword = findViewById(R.id.input_password)
        buttonLogin = findViewById(R.id.button_login)
        buttonGoRegister = findViewById(R.id.button_go_register)

        val db = DatabaseProvider.getDatabase(this)
        val userRepository = UserRepository(db.userDao())
        val sessionManager = SessionManager(this)

        buttonLogin.setOnClickListener {
            val username = inputUsername.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_field_error), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val success = withContext(Dispatchers.IO) {
                    userRepository.login(username, password)
                }
                if (success) {
                    sessionManager.isLoggedIn = true
                    sessionManager.username = username
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.login_success, username),
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, getString(R.string.login_failure), Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}


