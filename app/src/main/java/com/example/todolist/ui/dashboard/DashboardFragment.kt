package com.example.todolist.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentDashboardBinding
import kotlin.getValue

class DashboardFragment : BaseFragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    val dashBoardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initData() {
    }

    override fun bindComponent() {

    }

    override fun bindData() {
        dashBoardViewModel.text.observe(viewLifecycleOwner) {
            binding.textDashboard.text = it
        }
    }

    override fun bindEvent() {

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}