package com.asrul.jffplus.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_item.view.*
import java.util.ArrayList

class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object {
        const val roundedValue = 32
    }

    private var movieList = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
            this.movieList.clear()
            this.movieList.addAll(movies)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movies = movieList[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                tv_title.text = movie.title
                tv_year.text = movie.releaseDate
                tv_about.text = movie.overview
                val rating = movie.voteAverage.div(2)
                rating_bar.rating = rating.toFloat()

                Glide.with(itemView.context)
                    .load(BuildConfig.POSTER_URL+movie.posterPath)
                        .transform(CenterCrop(), RoundedCorners(roundedValue))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                        .error(R.drawable.ic_baseline_broken_image_24))
                    .into(img_poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}