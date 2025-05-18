package com.evoteckgeospatialconsult

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.evoteckgeospatialconsult.core.ui.MainViewModel
import com.evoteckgeospatialconsult.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup NavController and assign appropriate graph
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        // Initially hide bottom nav until we know where the user is
        binding.bottomNavigation.visibility = View.GONE

        setupNavGraphOnce()
        setupBottomNavVisibility()
    }

    private fun setupNavGraphOnce() {
        lifecycleScope.launch {
            viewModel.isUserLoggedIn.collectLatest { isLoggedIn ->
                if (!viewModel.hasSetGraph.value) {
                    val navInflater = navController.navInflater
                    val graph = navInflater.inflate(R.navigation.root_nav_graph)
                    /*graph.setStartDestination(
                        if (isLoggedIn) {
                            R.id.app_nav_graph
                        } else {
                            R.id.auth_nav_graph
                        }
                    )*/
                    navController.graph = graph
                    viewModel.markGraphAsSet() // mark it in ViewModel

                    // update bottom nav visibility immediately after setting the graph
                    updateBottomNavVisibility(navController.currentDestination?.id)
                }
            }
        }
    }

    private fun setupBottomNavVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateBottomNavVisibility(destination.id)
        }
    }
    private fun updateBottomNavVisibility(destinationId: Int?) {
        // List of fragments where the bottomNav should be hidden
        val hideBottomNav = setOf(
            R.id.splashFragment,
            R.id.welcomeFragment,
            R.id.loginFragment,
            R.id.signupFragment
        )
        binding.bottomNavigation.visibility =
            if (destinationId in hideBottomNav) View.GONE else View.VISIBLE
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // ensure bottom nav visibility is correct after configuration changes
        updateBottomNavVisibility(navController.currentDestination?.id)
    }

    private fun setupBottomNavigation() {
        /***
         * Bottom Navigation Setup:
         * --- courses
         * --- search
         * --- classroom
         * --- shortlist
         * --- profile
         */
    }
}