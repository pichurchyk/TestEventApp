package com.pichurchyk.testeventapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.pichurchyk.testeventapp.R
import com.pichurchyk.testeventapp.databinding.ActivityMainBinding
import com.pichurchyk.testeventapp.utils.visibleIf

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController

        setupWithNavController(binding.bottomNav, navController = navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.visibleIf(Screens.screensWithNav.contains(destination.id))
        }
    }
}
