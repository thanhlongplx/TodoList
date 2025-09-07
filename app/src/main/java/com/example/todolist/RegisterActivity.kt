package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todolist.data.database.DatabaseProvider
import com.example.todolist.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    private lateinit var inputUsername: EditText
    private lateinit var inputPassword: EditText
    private lateinit var inputPasswordConfirm: EditText
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        inputUsername = findViewById(R.id.input_username)
        inputPassword = findViewById(R.id.input_password)
        inputPasswordConfirm = findViewById(R.id.input_password_confirm)
        buttonRegister = findViewById(R.id.button_register)

        val db = DatabaseProvider.getDatabase(this)
        val userRepository = UserRepository(db.userDao())

        buttonRegister.setOnClickListener {
            val username = inputUsername.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            val confirm = inputPasswordConfirm.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_field_error), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != confirm) {
                Toast.makeText(this, getString(R.string.password_not_match), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val isExists = withContext(Dispatchers.IO) {
                    userRepository.isUsernameExists(username)
                }
                if (isExists) {
                    Toast.makeText(this@RegisterActivity, getString(R.string.register_failure), Toast.LENGTH_SHORT).show()
                } else {
                    withContext(Dispatchers.IO) {
                        userRepository.register(username, password)
                    }
                    Toast.makeText(this@RegisterActivity, getString(R.string.register_success), Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}


