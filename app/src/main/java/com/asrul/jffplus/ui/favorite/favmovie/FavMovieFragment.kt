package com.asrul.jffplus.ui.favorite.favmovie

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
import kotlinx.android.synthetic.main.fragment_movie.*

class FavMovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]

            viewModel.insertBait()

            val movieAdapter = FavMoviePagedAdapter()
            viewModel.favMovies.observe(this, Observer { movie ->
                if (movie != null) {
                    when (movie.status){
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            movieAdapter.submitList(movie.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, resources.getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)
            rv_movie.adapter = movieAdapter
        }
    }
}