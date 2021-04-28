package com.asrul.jffplus.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asrul.jffplus.data.remote.api.TvItem
import com.asrul.jffplus.databinding.FragmentTvShowBinding
import com.asrul.jffplus.ui.detail.DetailActivity
import com.asrul.jffplus.utils.ViewModelFactory

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        val tvShowAdapter = TvShowAdapter()
        viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->
            tvShowAdapter.setTvShow(tvShow)
            tvShowAdapter.notifyDataSetChanged()
            binding?.progressBar?.visibility = View.GONE
        })

        binding?.rvTvShow?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        tvShowAdapter.setOnItemClickCallbackListener(object: TvShowAdapter.OnItemClickCallbackListener {
            override fun onItemClicked(tvShow: TvItem) {
                val id: String = tvShow.id.toString()
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TV_SHOW, id)
                startActivity(intent)
            }
        })
    }
}