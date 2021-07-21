package com.thetechguy.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.addTextFragment) {
                toolbar.visibility = View.GONE
            }
            if(destination.id == R.id.homeFragment) {
                toolbar.visibility = View.GONE

            }
            if(destination.id == R.id.viewTextFragment) {
                            toolbar.visibility = View.GONE

                        }

            else {
                toolbar.visibility = View.GONE

            }



        }




        setSupportActionBar(toolbar)//replace appbar with toolbar
        setupActionBarWithNavController(navController)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MainViewModel::class.java)




    }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || return super.onSupportNavigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)||return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment


        if (menu?.findItem(R.id.addTextFragment)?.isEnabled == true)
        {
            toolbar.visibility = View.GONE
        }


//        menu?.findItem(R.id.save)?.isVisible = menu?.findItem(R.id.addTextFragment)?.isEnabled == false


//        menu?.findItem(R.id.settingsFragment)?.isEnabled = navHostFragment.childFragmentManager.fragments[0] !is SettingsFragment
        println("this is second commit change")
        return true
    }




}