package com.evoteckgeospatialconsult

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.evoteckgeospatialconsult.core.ui.MainViewModel
import com.evoteckgeospatialconsult.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "Main Activity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var hideBottomNav: Set<Int>

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        // Setup NavController and assign appropriate graph
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        // Only show up button on non-top-level fragments
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.courseListFragment,
                R.id.searchFragment,
                R.id.classroomFragment,
                R.id.shortlistFragment,
                R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_cart -> {
                    /*if (navController.currentDestination?.id != R.id.cartFragment) {
                        navController.navigate(R.id.cartFragment)
                    }*/
                    Toast.makeText(this,"Cart clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // track navigation changes for back stack
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            // Fragments where toolbar or bottomNav should be hidden
            val hideToolbar = setOf(
                R.id.splashFragment,
                R.id.welcomeFragment,
                R.id.loginFragment,
                R.id.signupFragment,
                R.id.termsWebViewFragment
            )
            hideBottomNav = setOf(
                R.id.splashFragment,
                R.id.welcomeFragment,
                R.id.loginFragment,
                R.id.signupFragment,
                R.id.termsWebViewFragment,
//                R.id.courseDetailsFragment,
//                R.id.searchResultsFragment,
//                R.id.classroomDetailsFragment,
//                R.id.profileDetailsFragment,
//                R.id.cartFragment,
//                R.id.shortlistDetailsFragment,
//                R.id.profileEditFragment,
            )

            // show/hide toolbar
            binding.toolbar.visibility = if (destination.id in hideToolbar) View.GONE else View.VISIBLE

            // show/hide bottom navigation
            binding.bottomNavigation.visibility = if (destination.id in hideBottomNav) View.GONE else View.VISIBLE

            binding.toolbar.title = when (destination.id) {
                R.id.searchFragment -> "Search"
                R.id.classroomFragment -> "Classroom"
                R.id.shortlistFragment -> "Shortlist"
                R.id.profileFragment -> "Profile"
//                R.id.cartFragment -> "Cart"
                else -> destination.label ?: getString(R.string.app_name)
            }
            Log.d(LOG_TAG, "Navigated to:  ${destination.label}")
        }
        setupNavGraphOnce()
    }

    private fun setupNavGraphOnce() {
        lifecycleScope.launch {
            viewModel.isUserLoggedIn.collectLatest { isLoggedIn ->
                if (!viewModel.hasSetGraph.value) {
                    val navInflater = navController.navInflater
                    val graph = navInflater.inflate(R.navigation.root_nav_graph)
                    navController.graph = graph
                    viewModel.markGraphAsSet() // mark it in ViewModel

                    // update bottom nav visibility immediately after setting the graph
                    updateBottomNavVisibility(navController.currentDestination?.id)
                }
            }
        }
    }

    private fun updateBottomNavVisibility(destinationId: Int?) {
        binding.bottomNavigation.visibility =
            if (destinationId in hideBottomNav) View.GONE else View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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