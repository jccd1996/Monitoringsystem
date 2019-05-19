package com.jccd.monitoringsystem.ui.historylist

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.jccd.monitoringsystem.R


class HistoryActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var message: Int = 0
    private lateinit var bundle: Bundle
    private lateinit var navOptions: NavOptions
    private val TYPE_KEY = "type"
    private lateinit var toolbar: Toolbar

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.dayHistoryFragment -> {
                if (navController.currentDestination!!.id != R.id.dayHistoryFragment) {
                    navController.popBackStack(R.id.dayHistoryFragment, true)
                    navController.navigate(
                        R.id.dayHistoryFragment,
                        bundle,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.weekHistoryFragment -> {
                if (navController.currentDestination!!.id != R.id.weekHistoryFragment) {
                    navController.popBackStack(R.id.weekHistoryFragment, true)
                    navController.navigate(
                        R.id.weekHistoryFragment,
                        bundle,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.monthHistoryFragment -> {
                if (navController.currentDestination!!.id != R.id.monthHistoryFragment) {
                    navController.popBackStack(R.id.monthHistoryFragment, true)
                    navController.navigate(
                        R.id.monthHistoryFragment,
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
        setContentView(R.layout.activity_history)
        toolbar = findViewById(R.id.toolbar)
        sendBundle()
        customToolbar()
        navigationSetUp()
    }

    fun destinationFragment(destination: Int): NavOptions {
        navOptions = NavOptions.Builder()
            .setPopUpTo(destination, true)
            .build()
        return navOptions
    }

    fun navigationSetUp() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        findNavController(R.id.fragment_container_temperature_history).setGraph(
            R.navigation.history_graph,
            bundle
        )
        navController = Navigation.findNavController(this, R.id.fragment_container_temperature_history)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    fun sendBundle() {
        val intent = intent
        message = intent.getIntExtra(TYPE_KEY, 0)
        bundle = Bundle()
        bundle.putInt(TYPE_KEY, message)
    }

    fun customToolbar() {
        when (message) {
            1 -> toolbar.title = this.getString(R.string.menu_temperature)
            2 -> toolbar.title = this.getString(R.string.menu_level_water)
            3 -> toolbar.title = this.getString(R.string.menu_ph)
        }
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
}
