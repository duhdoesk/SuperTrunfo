package com.duhdoesk.supertrunfoclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.duhdoesk.supertrunfoclone.databinding.ActivityMainBinding
import com.duhdoesk.supertrunfoclone.ui.collection.CollectionFragment
import com.duhdoesk.supertrunfoclone.ui.collection.CollectionFragmentFactory
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)



//        //set navHostFragment and navController
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        findViewById<NavigationView>(R.id.nav_view)
//            .setupWithNavController(navController)
//
//        drawerLayout = findViewById(R.id.drawerLayout)
//
//        //set AppBar
//        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//
//        navController.addOnDestinationChangedListener(listener = NavController.OnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.destination_gameOver || destination.id == R.id.destination_gameWon) {
//                supportActionBar?.setDisplayHomeAsUpEnabled(false)
//            } else {
//                supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            }
//        })

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}