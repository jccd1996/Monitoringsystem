package com.jccd.monitoringsystem.ui.main.ui

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.User
import com.jccd.monitoringsystem.ui.LoginActivity
import com.jccd.monitoringsystem.ui.main.IMainMVP
import com.jccd.monitoringsystem.ui.main.MainPresenter
import kotlinx.android.synthetic.main.nav_header_main.view.*
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    IMainMVP.view {


    private lateinit var presenter: IMainMVP.presenter
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navOptions: NavOptions
    private lateinit var manager: DownloadManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        presenter.validateLogUser()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        navController = Navigation.findNavController(this, R.id.fragment_container_temperature_history)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        // NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)

        NavigationUI.setupWithNavController(navView, navController)

        navView.setNavigationItemSelectedListener(this)
        presenter.loadDataUser()

        val user: User? = presenter.loadDataUser()
        if (user != null){
            setDataToNavDrawer(user!!)
        }else{
            finishActivity()
        }

    }

    override fun onBackPressed() {
        drawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun finishActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
    }

    override fun getMainActivity(): Activity = this

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        when (item.itemId) {
            R.id.nav_temperature -> {
                if (navController.currentDestination!!.id != R.id.temperatureFragment) {
                    navController.popBackStack(R.id.temperatureFragment, true)
                    navController.navigate(
                        R.id.temperatureFragment,
                        null,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
            }
            R.id.nav_level_water -> {
                if (navController.currentDestination!!.id != R.id.waterLevelFragment) {
                    navController.popBackStack(R.id.waterLevelFragment, true)
                    navController.navigate(
                        R.id.waterLevelFragment,
                        null,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
            }
            R.id.nav_level_ph -> {
                if (navController.currentDestination!!.id != R.id.phFragment) {
                    navController.popBackStack(R.id.phFragment, true)
                    navController.navigate(
                        R.id.phFragment,
                        null,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
            }
            R.id.nav_history -> {
                if (navController.currentDestination!!.id != R.id.historyFragment) {
                    navController.popBackStack(R.id.historyFragment, true)
                    navController.navigate(
                        R.id.historyFragment,
                        null,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }
            }
            R.id.nav_archive -> {
                if (navController.currentDestination!!.id != R.id.listImportantFragment) {
                    navController.popBackStack(R.id.listImportantFragment, true)
                    navController.navigate(
                        R.id.listImportantFragment,
                        null,
                        destinationFragment(navController.currentDestination!!.id)
                    )
                }

            }
            R.id.nav_download -> {
                if (isStoragePermissionGranted()){
                    presenter.downloadAllDataExcel(manager)
                }else{
                    Log.d("NOTIENE","NONONONO")
                }

            }
            R.id.nav_log_out -> {
                presenter.closeSession()
                return true
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    fun setDataToNavDrawer(user: User) {
        val navHead = findViewById<View>(R.id.nav_view) as NavigationView
        val head = navHead.getHeaderView(0)
        head.tvName.text = user.fullName
        head.tvEmail.text = user.email
    }

    fun destinationFragment(destination: Int): NavOptions {
        navOptions = NavOptions.Builder()
            .setPopUpTo(destination, true)
            .build()
        return navOptions
    }

    override fun onResume() {
        super.onResume()
        presenter.loadDataUser()
    }

    fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {

                ActivityCompat.requestPermissions(this, arrayOf(permission.WRITE_EXTERNAL_STORAGE), 1)
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            true
        }
    }
}
