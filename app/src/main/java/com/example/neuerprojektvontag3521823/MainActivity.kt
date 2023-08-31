package com.example.neuerprojektvontag3521823

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.neuerprojektvontag3521823.databinding.ActivityMainBinding
import com.example.neuerprojektvontag3521823.ui.MusicViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MusicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavBar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.bottomMusicPlayer -> binding.bottomNavBar.visibility = View.GONE
                else -> binding.bottomNavBar.visibility = View.VISIBLE
            }
        }
    }

    fun hideBottomControll() {
        binding.bottomMusicPlayer.visibility = View.GONE
    }

    fun showBottomControll() {
        binding.bottomMusicPlayer.visibility = View.VISIBLE
    }
}









