package com.asrul.jffplus.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asrul.jffplus.R
import com.asrul.jffplus.data.MovieEntity
import com.asrul.jffplus.databinding.RowItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = ArrayList<MovieEntity>()

    private lateinit var onItemClickCallbackListener: OnItemClickCallbackListener

    fun setOnItemClickCallback(onItemClickCallbackListener: OnItemClickCallbackListener) {
        this.onItemClickCallbackListener = onItemClickCallbackListener
    }

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies.isNullOrEmpty()) return
        this.movieList.clear()
        this.movieList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: RowItemBinding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                tvYear.text = movie.year
                tvAbout.text = movie.about
                tvDirector.text = itemView.resources.getString(R.string.director, movie.director)

                Glide.with(itemView.context)
                    .load(movie.posterPath)
                    .transform(CenterCrop(), RoundedCorners(32))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                            .error(R.drawable.ic_baseline_broken_image_24)
                    )
                    .into(imgPoster)

                itemView.setOnClickListener {
                    onItemClickCallbackListener.onItemClicked(movie)
                }
            }
        }
    }

    interface OnItemClickCallbackListener {
        fun onItemClicked(movie: MovieEntity)
    }
}