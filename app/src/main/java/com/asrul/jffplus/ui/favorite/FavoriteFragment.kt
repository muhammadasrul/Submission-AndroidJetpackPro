package com.asrul.jffplus.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asrul.jffplus.databinding.FragmentFavoriteBinding
import com.asrul.jffplus.ui.favorite.favmovie.FavMovieFragment
import com.asrul.jffplus.ui.favorite.favtvshow.FavTvShowFragment
import com.asrul.jffplus.utils.SectionPagerAdapter

class FavoriteFragment : Fragment() {

    private lateinit var sectionPagerAdapter: SectionPagerAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        sectionPagerAdapter.addFragment(FavMovieFragment())
        sectionPagerAdapter.addFragment(FavTvShowFragment())
        binding?.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
}