package com.example.todolist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.repository.PreferenceRepository
class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private  val  prefRepo: PreferenceRepository by lazy {
        PreferenceRepository(requireContext())
    }
    val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun bindEvent() {
        binding.apply {
            btnRegister.setOnClickListener {
                val username = editUsername.text.toString().trim()
                val password = editPassword.text.toString().trim()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Vui lòng nhập đầy đủ thông tin",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    prefRepo.register(username, password)
                    Toast.makeText(
                        requireContext(),
                        "Đăng ký thành công! Hãy đăng nhập tài khoản vừa đăng kí!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // Xử lý nút Đăng nhập
            btnLogin.setOnClickListener {
                val username = editUsername.text.toString().trim()
                val password = editPassword.text.toString().trim()

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
        homeViewModel.text.observe(viewLifecycleOwner) {

        }
    }
}
