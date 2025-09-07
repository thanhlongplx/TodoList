package com.example.todolist.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.data.database.DatabaseProvider
import com.example.todolist.data.repository.UserRepository
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val userRepository: UserRepository by lazy {
        UserRepository(DatabaseProvider.getDatabase(requireContext()).userDao())
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
                        getString(R.string.empty_field_error),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewLifecycleOwner.lifecycleScope.launch {
                        // check username
                        if (userRepository.isUsernameExists(username)) {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.register_failure),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            try {
                                userRepository.register(username, password)
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.register_success),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } catch (e: Exception) {
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.register_failure),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }

            btnLogin.setOnClickListener {
                val username = editUsername.text.toString().trim()
                val password = editPassword.text.toString().trim()

                viewLifecycleOwner.lifecycleScope.launch {
                    if (userRepository.login(username, password)) {
                        // Lưu username vào SharedPreferences khi đăng nhập thành công
                        val sharedPreferences = requireContext().getSharedPreferences("TodoListPrefs", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("current_username", username).apply()
                        Toast.makeText(
                            requireContext(),
                            String.format(getString(R.string.login_success), username),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.login_failure),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        homeViewModel.text.observe(viewLifecycleOwner) {
            // Không cần xử lý text ở đây nếu không sử dụng
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}