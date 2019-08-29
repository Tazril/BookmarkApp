package com.taz.bookmarkapp

import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.taz.bookmarkapp.helpers.ApiClient
import com.taz.bookmarkapp.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override lateinit var progressBar: ProgressBar
    override var toolbar: Toolbar? = null

    private lateinit var navController: NavController

    override fun initActivity() {
        progressBar = progressBarMain
        toolbar = toolbarMain as Toolbar
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.fragmentHostMain)
        setupActionBarWithNavController(navController)

    }

    override fun onAttachFragment(fragment: Fragment) {
        ApiClient.instantiate(this)
        super.onAttachFragment(fragment)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}