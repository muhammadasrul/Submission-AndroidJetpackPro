package com.asrul.jffplus.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.asrul.jffplus.R
import com.asrul.jffplus.databinding.ActivityMainBinding
import com.asrul.jffplus.ui.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home_nav -> {
                    loadFragment(HomeFragment())
                    supportActionBar?.title = resources.getString(R.string.app_full_name)
                }
                R.id.favorite_nav -> {
                    loadFragment(FavoriteFragment())
                    supportActionBar?.title = resources.getString(R.string.favorite)
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