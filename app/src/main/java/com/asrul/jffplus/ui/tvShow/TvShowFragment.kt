package com.asrul.jffplus.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asrul.jffplus.data.TvShowEntity
import com.asrul.jffplus.databinding.FragmentTvShowBinding
import com.asrul.jffplus.ui.detail.DetailActivity

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowViewModel::class.java]
            val tvShows = viewModel.getTvShow()

            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setTvShow(tvShows)

            binding.rvTvShow.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }

            tvShowAdapter.setOnItemClickCallbackListener(object: TvShowAdapter.OnItemClickCallbackListener {
                override fun onItemClicked(tvShow: TvShowEntity) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV_SHOW, tvShow.tvShowId)
                    startActivity(intent)
                }

            })
        }
    }
}