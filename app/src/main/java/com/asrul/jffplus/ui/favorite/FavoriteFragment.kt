package com.asrul.jffplus.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asrul.jffplus.R
import com.asrul.jffplus.ui.favorite.favmovie.FavMovieFragment
import com.asrul.jffplus.ui.favorite.favtvshow.FavTvShowFragment
import com.asrul.jffplus.utils.SectionPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class FavoriteFragment : Fragment() {

    private lateinit var sectionPagerAdapter: SectionPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        sectionPagerAdapter.addFragment(FavMovieFragment())
        sectionPagerAdapter.addFragment(FavTvShowFragment())
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }
}