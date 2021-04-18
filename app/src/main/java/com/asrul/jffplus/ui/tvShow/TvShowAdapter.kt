package com.asrul.jffplus.ui.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asrul.jffplus.R
import com.asrul.jffplus.data.TvShowEntity
import com.asrul.jffplus.databinding.RowItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<TvShowEntity>()

    private lateinit var onItemClickCallbackListener: OnItemClickCallbackListener

    fun setOnItemClickCallbackListener(onItemClickCallbackListener: OnItemClickCallbackListener) {
        this.onItemClickCallbackListener = onItemClickCallbackListener
    }

    fun setTvShow(tvShows: List<TvShowEntity>?) {
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

    inner class TvShowViewHolder(private val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.title
                tvYear.text = tvShow.year
                tvAbout.text = tvShow.about
                tvDirector.text = itemView.resources.getString(R.string.director, tvShow.director)

                Glide.with(itemView.context)
                    .load(tvShow.posterPath)
                        .centerCrop()
                        .transform(RoundedCorners(32))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                        .error(R.drawable.ic_baseline_broken_image_24))
                    .into(imgPoster)

                itemView.setOnClickListener {
                    onItemClickCallbackListener.onItemClicked(tvShow)
                }
            }
        }
    }

    interface OnItemClickCallbackListener {
        fun onItemClicked(tvShow: TvShowEntity)
    }
}