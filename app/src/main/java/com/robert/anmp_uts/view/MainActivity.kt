package com.robert.anmp_uts.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.robert.anmp_uts.R
import com.robert.anmp_uts.databinding.ActivityMainBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.robert.anmp_uts.model.User
import com.robert.anmp_uts.viewmodel.LoginViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get navigation host fragment from fragment container view conversion
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        // Obtain NavController from NavHostFragment
        navController = navHostFragment.navController

        // Set up bottom navigation with NavController and navigation graph
        binding.bottomMenu.setupWithNavController(navController)

        // Set up drawer navigation with NavController
        binding.navView.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        // Observe the current destination of NavController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.signUpFragment) {
                // Disable and hide the bottom navigation when the login fragment or signup fragment is displayed
                binding.bottomMenu.visibility = View.GONE
                binding.bottomMenu.isEnabled = false
                // Close the drawer layout for the login and signup
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                // Hide the drawer button in action bar
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                binding.bottomMenu.visibility = View.VISIBLE
                binding.bottomMenu.isEnabled = true
                binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                // Show the drawer button in action bar
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {

        val sharedPrefs = getSharedPreferences("com.robert.anmp_uts", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
        super.onDestroy()
    }
}
