package com.asrul.jffplus.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asrul.jffplus.data.remote.api.MovieItem
import com.asrul.jffplus.databinding.FragmentMovieBinding
import com.asrul.jffplus.ui.detail.DetailActivity
import com.asrul.jffplus.utils.ViewModelFactory

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val movieAdapter = MovieAdapter()
        viewModel.getMovie().observe(viewLifecycleOwner, { movie ->
            movieAdapter.setMovies(movie)
            movieAdapter.notifyDataSetChanged()
            binding?.progressBar?.visibility = View.GONE
        })

        binding?.apply {
            rvMovie.layoutManager = LinearLayoutManager(context)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = movieAdapter
        }

        movieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallbackListener {
            override fun onItemClicked(movie: MovieItem) {
                val id: String = movie.id.toString()
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_MOVIE, id)
                startActivity(intent)
            }

        })
    }
}