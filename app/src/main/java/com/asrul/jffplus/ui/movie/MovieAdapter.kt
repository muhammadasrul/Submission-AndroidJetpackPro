package com.asrul.jffplus.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.databinding.RowItemBinding
import com.asrul.jffplus.utils.MovieItemClickCallbackListener
import java.util.*

class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = ArrayList<MovieEntity>()
    private lateinit var movieItemClickCallbackListener: MovieItemClickCallbackListener

    fun setOnItemClickCallback(movieItemClickCallbackListener: MovieItemClickCallbackListener) {
        this.movieItemClickCallbackListener = movieItemClickCallbackListener
    }

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
            this.movieList.clear()
            this.movieList.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = movieList[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                tvYear.text = movie.releaseDate
                tvAbout.text = movie.overview
                val rating = movie.voteAverage.div(VOTE_AVERAGE_DIV)
                ratingBar.rating = rating.toFloat()

                imgPoster.load(BuildConfig.POSTER_URL + movie.posterPath) {
                    placeholder(R.drawable.ic_baseline_refresh_24)
                    error(R.drawable.ic_baseline_broken_image_24)
                    transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                }

                itemView.setOnClickListener {
                    movieItemClickCallbackListener.onItemClicked(movie)
                }
            }
        }
    }

    companion object {
        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}