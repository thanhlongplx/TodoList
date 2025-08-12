package com.example.todolist

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todolist.base.BaseActivity
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : BaseActivity(R.layout.activity_main) {
  override  val navHostFragment by lazy { this.findViewById<FragmentContainerView>(R.id.nav_host_fragment_activity_main).getFragment() as? NavHostFragment }
  private  val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun initData() {

    }

    override fun bindData() {

    }

    override fun bindEvent() {

    }

    override fun bindComponent() {
        val navController = navHostFragment?.findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard,
            )
        )
        if (navController != null) {
            setupActionBarWithNavController(navController, appBarConfiguration)
            binding.navView.setupWithNavController(navController)
        }
    }
}