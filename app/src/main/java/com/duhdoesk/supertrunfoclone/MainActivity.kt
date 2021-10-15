package com.duhdoesk.supertrunfoclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set navHostFragment and navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)

        //set AppBar
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout = null)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout = null)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}