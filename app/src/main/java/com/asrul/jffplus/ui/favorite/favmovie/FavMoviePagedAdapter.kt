package com.asrul.jffplus.ui.favorite.favmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.databinding.RowItemBinding
import com.asrul.jffplus.utils.MovieItemClickCallbackListener

class FavMoviePagedAdapter :
    PagedListAdapter<MovieEntity, FavMoviePagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var movieItemClickCallbackListener: MovieItemClickCallbackListener

    fun setOnItemClickCallback(movieItemClickCallbackListener: MovieItemClickCallbackListener) {
        this.movieItemClickCallbackListener = movieItemClickCallbackListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavMoviePagedAdapter.ViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavMoviePagedAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }

        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}