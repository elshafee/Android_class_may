package com.elshafee.androidclassmay

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.elshafee.androidclassmay.fragments.DetailsFragment
import com.elshafee.androidclassmay.fragments.EmailFragment
import com.elshafee.androidclassmay.fragments.OurEvents
import com.elshafee.androidclassmay.fragments.PasswordFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navview = findViewById<NavigationView>(R.id.navView)
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val email = OurEvents()
        val password = PasswordFragment()
        val details = DetailsFragment()

        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navview.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.email_fl -> {
                  setCurrentFragment(email)
                }

                R.id.password_fl -> {
                    setCurrentFragment(password)
                }

                R.id.details_fl -> {
                    setCurrentFragment(details)
                }
            }
            true
        }

        bottomnav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.email_fl -> {
                    setCurrentFragment(email)
                }

                R.id.password_fl -> {
                    setCurrentFragment(password)
                }

                R.id.details_fl -> {
                    setCurrentFragment(details)
                }
            }
            true
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}