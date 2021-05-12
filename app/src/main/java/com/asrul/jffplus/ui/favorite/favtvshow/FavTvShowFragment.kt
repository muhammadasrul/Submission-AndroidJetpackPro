package com.asrul.jffplus.ui.favorite.favtvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.databinding.FragmentTvShowBinding
import com.asrul.jffplus.ui.detail.DetailActivity
import com.asrul.jffplus.utils.TvShowItemClickCallbackListener
import com.asrul.jffplus.utils.ViewModelFactory
import com.asrul.jffplus.vo.Status

class FavTvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(requireActivity(), factory)[FavTvShowViewModel::class.java]

        viewModel.insertBait()

        val tvShowAdapter = FavTvShowPagedAdapter()
        viewModel.favTvShow?.observe(this, { tvShow ->
            if (tvShow != null) {
                when (tvShow.status) {
                    Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.progressBar?.visibility = View.GONE
                        tvShowAdapter.submitList(tvShow.data)
                        tvShowAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(context, resources.getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding?.apply {
            rvTvShow.layoutManager = LinearLayoutManager(context)
            rvTvShow.setHasFixedSize(true)
            rvTvShow.adapter = tvShowAdapter

            tvShowAdapter.setOnItemClickCallback(object: TvShowItemClickCallbackListener {
                override fun onItemClicked(tvShow: TvShowEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV_SHOW, tvShow)
                    startActivity(intent)
                }
            })
        }
    }
}