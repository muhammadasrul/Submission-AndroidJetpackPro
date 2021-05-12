package com.asrul.jffplus.ui.favorite.favtvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.databinding.RowItemBinding
import com.asrul.jffplus.ui.detail.DetailActivity
import com.asrul.jffplus.utils.TvShowItemClickCallbackListener

class FavTvShowPagedAdapter: PagedListAdapter<TvShowEntity, FavTvShowPagedAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var tvShowItemClickCallbackListener: TvShowItemClickCallbackListener

    fun setOnItemClickCallback(tvShowItemClickCallbackListener: TvShowItemClickCallbackListener) {
        this.tvShowItemClickCallbackListener = tvShowItemClickCallbackListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding  = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        tvShow?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.name
                tvYear.text = tvShow.firstAirDate
                tvAbout.text = tvShow.overview
                val rating = tvShow.voteAverage.div(VOTE_AVERAGE_DIV)
                ratingBar.rating = rating.toFloat()

                imgPoster.load(BuildConfig.POSTER_URL + tvShow.posterPath) {
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }

        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}