package com.example.possumuschallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.possumuschallenge.databinding.ActivityMainBinding
import com.example.possumuschallenge.ui.SharedViewModel
import com.example.possumuschallenge.utils.UiEvents
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.setupWithNavController(navController)

        setUpObservers()
    }

    private fun setUpObservers(){
        lifecycleScope.launchWhenStarted {
            viewModel.uiEvents.collect() { event ->
                when (event) {
                    is UiEvents.Navigate -> navigate(navController,event.destination)
                    is UiEvents.ShowSnackBar -> showSnackBar(event.message,event.action, event.onClickListener)
                }
            }
        }
    }

    private fun navigate(navController: NavController, destination: NavDirections) {
        navController.navigate(destination)
    }


    private fun showSnackBar(message: String, action: String, onClickListener: () -> Unit) {
        Snackbar
            .make(binding.root, message, Snackbar.LENGTH_LONG)
            .setAction(action) {
                onClickListener()
            }.show()
    }
}