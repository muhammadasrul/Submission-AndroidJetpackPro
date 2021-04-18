package com.asrul.jffplus.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asrul.jffplus.data.MovieEntity
import com.asrul.jffplus.databinding.FragmentMovieBinding
import com.asrul.jffplus.ui.detail.DetailActivity

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = viewModel.getMovie()

            val movieAdapter = MovieAdapter()
            movieAdapter.setMovies(movies)

            binding.rvMovie.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            movieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallbackListener {
                override fun onItemClicked(movie: MovieEntity) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie.movieId)
                    startActivity(intent)
                }

            })
        }
    }
}