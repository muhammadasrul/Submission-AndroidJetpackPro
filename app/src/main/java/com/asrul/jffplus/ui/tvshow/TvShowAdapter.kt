package com.asrul.jffplus.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.remote.api.TvItem
import com.asrul.jffplus.databinding.RowItemBinding

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = ArrayList<TvItem>()

    private lateinit var onItemClickCallbackListener: OnItemClickCallbackListener

    fun setOnItemClickCallbackListener(onItemClickCallbackListener: OnItemClickCallbackListener) {
        this.onItemClickCallbackListener = onItemClickCallbackListener
    }

    fun setTvShow(tvShows: List<TvItem>) {
        if (tvShows.isNullOrEmpty()) return
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

    inner class TvShowViewHolder(private var binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvItem) {
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
                    onItemClickCallbackListener.onItemClicked(tvShow)
                }
            }
        }
    }

    interface OnItemClickCallbackListener {
        fun onItemClicked(tvShow: TvItem)
    }

    companion object {
        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}