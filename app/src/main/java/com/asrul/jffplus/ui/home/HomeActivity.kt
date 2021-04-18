package com.asrul.jffplus.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asrul.jffplus.R
import com.asrul.jffplus.databinding.ActivityHomeBinding
import com.asrul.jffplus.databinding.CarouselLayoutBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.synnapps.carouselview.ViewListener

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.app_title)
            elevation = 0f
        }

        binding.carouselView.apply {
            pageCount = carouselImg.size
            setViewListener(viewListener)
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private val carouselImg = arrayOf(
            "https://d32h7sewnio87v.cloudfront.net/production/posters-and-backdrops/1600x1100/film/65933/8748581c98be4e6e4b986dbc62af63d6.png",
            "https://d32h7sewnio87v.cloudfront.net/production/posters-and-backdrops/1600x1100/film/65918/0d38f37947d2afe001892788c5dea6b8.jpg",
            "https://d32h7sewnio87v.cloudfront.net/production/posters-and-backdrops/1600x1100/film/65910/0498a6ef32f3bf9f8f757eb7626dbcfd.png"
    )

    private val carouselTxt = arrayOf(
            "Talks & Event",
            "Film LineUp",
            "JFF Plus: Online Festival"
    )

    private val carouselDcs = arrayOf(
            "Special content of JFF Plus: Online Festival",
            "From the latest trending films to classic cinema, explore our special selection of the Japan’s acclaimed films.",
            "Join JFF PLus and enjoy Japanese films while the feastival goes on!"
    )

    private val viewListener = ViewListener { position ->
        val customBinding = CarouselLayoutBinding.inflate(layoutInflater)

        customBinding.apply {
            Glide.with(applicationContext)
                .load(carouselImg[position])
                .centerCrop()
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                    .error(R.drawable.ic_baseline_broken_image_24))
                .into(imgCarousel)
            txtCarousel.text = carouselTxt[position]
            txtCarouselDesc.text = carouselDcs[position]
        }
        return@ViewListener customBinding.root
    }
}