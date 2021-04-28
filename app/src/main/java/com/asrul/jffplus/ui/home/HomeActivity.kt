package com.asrul.jffplus.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import com.asrul.jffplus.R
import com.asrul.jffplus.databinding.ActivityHomeBinding
import com.asrul.jffplus.databinding.CarouselLayoutBinding
import com.synnapps.carouselview.ViewListener

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.carouselView.apply {
            pageCount = carouselImg.size
            setViewListener(viewListener)
        }

        initToolbar()

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)

        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private val carouselImg = intArrayOf(
        R.drawable.b_1,
        R.drawable.b_2,
        R.drawable.b_3
    )

    private val carouselTxt = arrayOf(
        "Talks & Event",
        "Film LineUp",
        "JFF Plus: Online Festival"
    )

    private val carouselDcs = arrayOf(
        "Special content of JFF Plus: Online Festival",
        "From the latest trending films to classic cinema, explore our special selection of the Japan’s acclaimed films.",
        "Join JFF PLus and enjoy Japanese films while the festival goes on!"
    )

    private val viewListener = ViewListener { position ->
        val customBinding = CarouselLayoutBinding.inflate(layoutInflater)

        customBinding.apply {
            imgCarousel.load(carouselImg[position]) {
                placeholder(R.drawable.ic_baseline_refresh_24)
                error(R.drawable.ic_baseline_broken_image_24)
            }
            txtCarousel.text = carouselTxt[position]
            txtCarouselDesc.text = carouselDcs[position]
        }
        return@ViewListener customBinding.root
    }

    private fun initToolbar() {
        binding.collapsingToolbar.apply {
            title = getString(R.string.app_title)
            setCollapsedTitleTextColor(ContextCompat.getColor(context, R.color.textColor))
            setExpandedTitleColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
    }
}