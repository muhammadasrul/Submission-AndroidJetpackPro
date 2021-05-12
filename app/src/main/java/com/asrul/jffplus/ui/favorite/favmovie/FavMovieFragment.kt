package com.asrul.jffplus.ui.favorite.favmovie

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
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.databinding.FragmentMovieBinding
import com.asrul.jffplus.ui.detail.DetailActivity
import com.asrul.jffplus.utils.MovieItemClickCallbackListener
import com.asrul.jffplus.utils.ViewModelFactory
import com.asrul.jffplus.vo.Status

class FavMovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]

        viewModel.insertBait()

        val movieAdapter = FavMoviePagedAdapter()
        viewModel.favMovies?.observe(this, { movie ->
            if (movie != null) {
                when (movie.status){
                    Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding?.progressBar?.visibility = View.GONE
                        movieAdapter.submitList(movie.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(context, resources.getString(R.string.terjadi_kesalahan), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        binding?.apply {
            rvMovie.layoutManager = LinearLayoutManager(context)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = movieAdapter

            movieAdapter.setOnItemClickCallback(object: MovieItemClickCallbackListener {
                override fun onItemClicked(movie: MovieEntity) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
                    startActivity(intent)
                }
            })
        }
    }
}