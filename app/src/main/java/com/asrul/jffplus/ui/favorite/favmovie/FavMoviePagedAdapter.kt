package com.asrul.jffplus.ui.favorite.favmovie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.ui.detail.DetailActivity
import com.asrul.jffplus.ui.movie.MovieAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_item.view.*

class FavMoviePagedAdapter: PagedListAdapter<MovieEntity, FavMoviePagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavMoviePagedAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavMoviePagedAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)!!
        holder.bind(movie)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity) {
            with(itemView) {
                tv_title.text = movie.title
                tv_year.text = movie.releaseDate
                tv_about.text = movie.overview
                val rating = movie.voteAverage.div(2)
                rating_bar.rating = rating.toFloat()

                Glide.with(itemView.context)
                    .load(BuildConfig.POSTER_URL + movie.posterPath)
                    .transform(CenterCrop(), RoundedCorners(MovieAdapter.roundedValue))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                            .error(R.drawable.ic_baseline_broken_image_24)
                    )
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