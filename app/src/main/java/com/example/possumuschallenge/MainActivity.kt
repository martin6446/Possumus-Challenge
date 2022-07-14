package com.example.possumuschallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.possumuschallenge.databinding.ActivityMainBinding
import com.example.possumuschallenge.ui.SharedViewModel
import com.example.possumuschallenge.utils.UiEvents
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)

        navigate(navController)
    }

    private fun navigate(navController: NavController) {
        lifecycleScope.launchWhenStarted {
            viewModel.uiEvents.collect { event ->
                when(event){
                    is UiEvents.Navigate -> {
                        navController.navigate(event.destiny)
                    }
                }
            }
        }
    }
}