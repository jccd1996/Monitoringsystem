package com.jccd.monitoringsystem.ui.temperature.history

import android.os.Bundle
import android.os.Message
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.ui.temperature.history.day_history.DayHistoryTemperatureFragment


class TemperatureHistoryActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private  var message: Int = 0
    private lateinit var bundle: Bundle
    private lateinit var navOptions: NavOptions

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.dayHistoryTemperatureFragment -> {
               // navController.navigate(R.id.dayHistoryTemperatureFragment,bundle)
                if (navController.currentDestination!!.id != R.id.dayHistoryTemperatureFragment) {
                    navController.popBackStack(R.id.dayHistoryTemperatureFragment, true)
                    navController.navigate(
                        R.id.dayHistoryTemperatureFragment,
                        bundle,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.weekHistoryTemperatureFragment -> {
              //  navController.navigate(R.id.weekHistoryTemperatureFragment,bundle)
                if (navController.currentDestination!!.id != R.id.weekHistoryTemperatureFragment) {
                    navController.popBackStack(R.id.weekHistoryTemperatureFragment, true)
                    navController.navigate(
                        R.id.weekHistoryTemperatureFragment,
                        bundle,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.monthHistoryTemperatureFragment -> {
              //  navController.navigate(R.id.monthHistoryTemperatureFragment,bundle)
                if (navController.currentDestination!!.id != R.id.monthHistoryTemperatureFragment) {
                    navController.popBackStack(R.id.monthHistoryTemperatureFragment, true)
                    navController.navigate(
                        R.id.monthHistoryTemperatureFragment,
                        bundle,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature_history)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val intent = intent
        message = intent.getIntExtra("type",0)
        bundle = Bundle()
        bundle.putInt("type",message)

        findNavController(R.id.fragment_container_temperature_history).setGraph(R.navigation.temperature_history_graph,bundle)
        navController= Navigation.findNavController(this,R.id.fragment_container_temperature_history)

       // navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    fun destinationFragment(destination: Int): NavOptions {
        navOptions = NavOptions.Builder()
            .setPopUpTo(destination, true)
            .build()
        return navOptions
    }

}
