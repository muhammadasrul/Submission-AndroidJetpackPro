package com.asrul.jffplus.ui.favorite.favtvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asrul.jffplus.R
import com.asrul.jffplus.utils.ViewModelFactory
import com.asrul.jffplus.vo.Status
import kotlinx.android.synthetic.main.fragment_tv_show.*

class FavTvShowFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[FavTvShowViewModel::class.java]

            viewModel.insertBait()

            val tvShowAdapter = FavTvShowPagedAdapter()
            viewModel.favTvShow.observe(this, Observer { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            tvShowAdapter.submitList(tvShow.data)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, resources.getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            rv_tv_show.layoutManager = LinearLayoutManager(context)
            rv_tv_show.setHasFixedSize(true)
            rv_tv_show.adapter = tvShowAdapter
        }
    }
}