package com.example.todolist.ui.dashboard

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
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.databinding.FragmentCreateTaskBinding
import kotlinx.coroutines.launch

class CreateTaskFragment : BaseFragment() {
    private val todoRepository: TodoRepository by lazy {
        TodoRepository(DatabaseProvider.getDatabase(requireContext()).todoDao())
    }
    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initData() {}

    override fun bindComponent() {}

    override fun bindData() {
        // Lấy username từ SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("TodoListPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("current_username", null)

        // Cập nhật ViewModel
        dashboardViewModel.setUsername(username)


        dashboardViewModel.text.observe(viewLifecycleOwner) { name ->
            if (!name.isNullOrEmpty()) {
                binding.tvUsername.text = "Xin chào, $name"
                binding.scrollView.visibility = View.VISIBLE
            } else {
                binding.tvUsername.text = getString(R.string.guest_greeting) // ví dụ: "Xin chào, Guest 👋"
                binding.scrollView.visibility = View.GONE // ẩn form
                Toast.makeText(requireContext(), "Vui lòng đăng nhập để thêm task!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun bindEvent() {

        binding.apply {
            btnAddTask.setOnClickListener {
                val title = inputTitle.text.toString()
                val description = inputDescription.text.toString()

                if (title.isEmpty() || description.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.empty_field_error),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // get username from SharedPreferences
                    val sharedPreferences = requireContext()
                        .getSharedPreferences("TodoListPrefs", Context.MODE_PRIVATE)
                    val byUser = sharedPreferences.getString("current_username", null).toString()
                    viewLifecycleOwner.lifecycleScope.launch {
                        try {
                            todoRepository.insert( title, description)
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.add_task_success),
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

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}