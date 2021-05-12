package com.asrul.jffplus.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.databinding.RowItemBinding
import com.asrul.jffplus.ui.detail.DetailActivity
import com.asrul.jffplus.utils.TvShowItemClickCallbackListener

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    var listTvShow = ArrayList<TvShowEntity>()
    private lateinit var tvShowItemClickCallbackListener: TvShowItemClickCallbackListener

    fun setOnItemClickCallback(tvShowItemClickCallbackListener: TvShowItemClickCallbackListener) {
        this.tvShowItemClickCallbackListener = tvShowItemClickCallbackListener
    }

    fun setTvShow(tvShows: List<TvShowEntity>?) {
        if (tvShows == null) return
            this.listTvShow.clear()
            this.listTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShows = listTvShow[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.name
                tvYear.text = tvShow.firstAirDate
                tvAbout.text = tvShow.overview
                val rating = tvShow.voteAverage.div(VOTE_AVERAGE_DIV)
                ratingBar.rating = rating.toFloat()

                imgPoster.load(BuildConfig.POSTER_URL+tvShow.posterPath) {
                    placeholder(R.drawable.ic_baseline_refresh_24)
                    error(R.drawable.ic_baseline_broken_image_24)
                    transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                }

                itemView.setOnClickListener {
                    tvShowItemClickCallbackListener.onItemClicked(tvShow)
                }
            }
        }
    }

    companion object {
        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}