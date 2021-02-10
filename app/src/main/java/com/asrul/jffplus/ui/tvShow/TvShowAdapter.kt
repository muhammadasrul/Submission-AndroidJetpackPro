package com.asrul.jffplus.ui.tvShow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_item.view.*

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    companion object {
        const val roundedValue = 32
    }

    var listTvShow = ArrayList<TvShowEntity>()

    fun setTvShow(tvShows: List<TvShowEntity>?) {
        if (tvShows == null) return
            this.listTvShow.clear()
            this.listTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShows = listTvShow[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listTvShow.size

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShowEntity) {
            with(itemView) {
                tv_title.text = tvShow.name
                tv_year.text = tvShow.firstAirDate
                tv_about.text = tvShow.overview
                val rating = tvShow.voteAverage.div(2)
                rating_bar.rating = rating.toFloat()

                Glide.with(itemView.context)
                    .load(BuildConfig.POSTER_URL+tvShow.posterPath)
                        .centerCrop()
                        .transform(RoundedCorners(roundedValue))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                        .error(R.drawable.ic_baseline_broken_image_24))
                    .into(img_poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV_SHOW, tvShow)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}