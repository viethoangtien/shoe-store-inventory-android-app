package com.example.showstoreinventory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.showstoreinventory.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpNavigation()
        setUpListener()
    }

    private fun setUpNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.shoeListFragment), binding.drawerLayout)
        binding.toolbar.apply {
            setupWithNavController(navController, appBarConfiguration)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.welcomeFragment, R.id.instructionFragment -> {
                    binding.toolbar.navigationIcon = null
                }

                R.id.shoeListFragment -> {
                    binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
                }

                R.id.shoeDetailFragment -> {
                    binding.toolbar.setNavigationIcon(R.drawable.ic_back)
                }
            }
        }
    }

    private fun setUpListener() {
        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawers()
            when (it.itemId) {
                R.id.menu_logout -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.toLoginFragment)
                }
            }
            true
        }
    }
}