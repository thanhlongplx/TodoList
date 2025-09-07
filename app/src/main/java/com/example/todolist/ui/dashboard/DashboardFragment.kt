package com.example.todolist.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolist.R
import com.example.todolist.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get username from SharedPreferences
        val sharedPreferences = requireContext()
            .getSharedPreferences("TodoListPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("current_username", null)

        // Show username or "Guest"
        binding.tvUsername.text = if (!username.isNullOrEmpty()) {
            "Xin chào, $username 👋"
        } else {
            getString(R.string.guest_greeting)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
