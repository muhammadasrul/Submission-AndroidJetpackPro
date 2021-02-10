package com.asrul.jffplus.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.utils.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val w: Window = window
        w.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        btn_back.setOnClickListener {
            onBackPressed()
        }

        val factory = ViewModelFactory.getInstance(baseContext)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val movieDetail: MovieEntity? = intent.getParcelableExtra(EXTRA_MOVIE)

        val tvShowDetail: TvShowEntity? = intent.getParcelableExtra(EXTRA_TV_SHOW)

        if (movieDetail != null) {
            viewModel.movieDetail.value = movieDetail
            viewModel.movieDetail.observe(this, Observer { movie ->
                progressBar.visibility = View.GONE
                Glide.with(this)
                        .load(BuildConfig.POSTER_URL+movie.posterPath)
                        .transform(CenterCrop(), RoundedCorners(32))
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                                .error(R.drawable.ic_baseline_broken_image_24))
                        .into(image_poster)

                Glide.with(this)
                        .load(BuildConfig.BACKDROP_URL+movie.backdropPath)
                        .centerCrop()
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                                .error(R.drawable.ic_baseline_broken_image_24))
                        .into(image_backdrop)

                text_title.text = movie.title
                text_release.text = movie.releaseDate
                text_popularity.text = movie.popularity
                text_vcount.text = movie.voteCount
                text_about.text = movie.overview
                val rating = movie.voteAverage.div(2)
                rating_bar.rating = rating.toFloat()
            })

            btn_fav.setOnClickListener {
                if (movieDetail.favorite) {
                    viewModel.setMovieFavoriteState(movieDetail, false)
                    btn_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    toast(resources.getString(R.string.remove_from_favorite))
                } else {
                    viewModel.setMovieFavoriteState(movieDetail, true)
                    btn_fav.setImageResource(R.drawable.ic_baseline_favorite_24)
                    toast(resources.getString(R.string.add_to_favorite))
                }
            }
            if (movieDetail.favorite) {
                btn_fav.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                btn_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }

        if (tvShowDetail != null) {
            viewModel.tvShowDetail.value = tvShowDetail
            viewModel.tvShowDetail.observe(this, Observer { tvShow ->
                progressBar.visibility = View.GONE
                Glide.with(this)
                        .load(BuildConfig.POSTER_URL+tvShow.posterPath)
                        .transform(CenterCrop(), RoundedCorners(32))
                        .apply (RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                                .error(R.drawable.ic_baseline_broken_image_24))
                        .into(image_poster)

                Glide.with(this)
                        .load(BuildConfig.BACKDROP_URL+tvShow.backdropPath)
                        .centerCrop()
                        .apply (RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                                .error(R.drawable.ic_baseline_broken_image_24))
                        .into(image_backdrop)

                text_title.text = tvShow.name
                text_release.text = tvShow.firstAirDate
                text_popularity.text = tvShow.popularity
                text_vcount.text = tvShow.voteCount
                text_about.text = tvShow.overview
                val rating = tvShow.voteAverage.div(2)
                rating_bar.rating = rating.toFloat()

                if (tvShow.favorite) {
                    btn_fav.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    btn_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            })

            btn_fav.setOnClickListener {
                if (tvShowDetail.favorite) {
                    viewModel.setTvShowFavoriteState(tvShowDetail, false)
                    btn_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    toast(resources.getString(R.string.remove_from_favorite))
                } else {
                    viewModel.setTvShowFavoriteState(tvShowDetail, true)
                    btn_fav.setImageResource(R.drawable.ic_baseline_favorite_24)
                    toast(resources.getString(R.string.add_to_favorite))
                }
            }
            if (tvShowDetail.favorite) {
                btn_fav.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                btn_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }
}