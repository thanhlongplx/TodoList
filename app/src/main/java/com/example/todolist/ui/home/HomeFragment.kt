package com.example.todolist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.repository.PreferenceRepository

class HomeFragment : Fragment() {

    private lateinit var prefRepo: PreferenceRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Khởi tạo PreferenceRepository
        prefRepo = PreferenceRepository(requireContext())

        val edtUsername = view.findViewById<EditText>(R.id.edit_username)
        val edtPassword = view.findViewById<EditText>(R.id.edit_password)
        val btnLogin = view.findViewById<Button>(R.id.btn_login)
        val btnRegister = view.findViewById<Button>(R.id.btn_register)

        // Xử lý nút Đăng ký
        btnRegister.setOnClickListener {
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                prefRepo.register(username, password)
                Toast.makeText(requireContext(), "Đăng ký thành công! Hãy đăng nhập tài khoản vừa đăng kí!", Toast.LENGTH_SHORT).show()
            }
        }

        // Xử lý nút Đăng nhập
        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (prefRepo.login(username, password)) {
                Toast.makeText(
                    requireContext(),
                    "Đăng nhập thành công! Xin chào $username",
                    Toast.LENGTH_SHORT
                ).show()
                // TODO: Chuyển sang màn hình khác

            } else {
                Toast.makeText(
                    requireContext(),
                    "Sai tài khoản hoặc mật khẩu!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}
