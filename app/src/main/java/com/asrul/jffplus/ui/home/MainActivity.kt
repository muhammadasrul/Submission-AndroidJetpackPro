package com.asrul.jffplus.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.asrul.jffplus.R
import com.asrul.jffplus.ui.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val favorite = resources.getString(R.string.favorite)
        val fullName = resources.getString(R.string.app_full_name)

        setSupportActionBar(toolbar)
        supportActionBar?.title = fullName
        supportActionBar?.elevation = 0f

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home_nav -> {
                    loadFragment(HomeFragment())
                    supportActionBar?.title = fullName
                }
                R.id.favorite_nav -> {
                    loadFragment(FavoriteFragment())
                    supportActionBar?.title = favorite
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_frame, fragment)
                .commit()
            return true
        }
        return false
    }
}