package com.elshafee.androidclassmay.breakingbadapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.elshafee.androidclassmay.R
import com.elshafee.androidclassmay.databinding.ActivityBreakingBadBinding

class BreakingBadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBreakingBadBinding
    private lateinit var navControll: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreakingBadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navControll = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf())
        setupActionBarWithNavController(navControll)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navControll, appBarConfiguration)
    }
}