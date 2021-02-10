package com.asrul.jffplus.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asrul.jffplus.R
import com.asrul.jffplus.ui.movie.MovieFragment
import com.asrul.jffplus.ui.tvShow.TvShowFragment
import com.asrul.jffplus.utils.SectionPagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.synnapps.carouselview.ViewListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.carousel_layout.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.carousel_view
import kotlinx.android.synthetic.main.fragment_home.tabs
import kotlinx.android.synthetic.main.fragment_home.view_pager

class HomeFragment : Fragment() {

    private lateinit var sectionPagerAdapter: SectionPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carousel_view.setViewListener(viewListener)
        carousel_view.pageCount = carouselImg.size

        sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        sectionPagerAdapter.addFragment(MovieFragment())
        sectionPagerAdapter.addFragment(TvShowFragment())
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)
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

    private val viewListener = ViewListener {position ->
        val customView: View = layoutInflater.inflate(R.layout.carousel_layout, null)

        with(customView) {
            Glide.with(customView.context)
                .load(carouselImg[position])
                .centerCrop()
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                    .error(R.drawable.ic_baseline_broken_image_24))
                .into(carousel_img)
            carousel_txt.text = carouselTxt[position]
            carousel_desc_txt.text = carouselDcs[position]
        }

        return@ViewListener customView
    }
}