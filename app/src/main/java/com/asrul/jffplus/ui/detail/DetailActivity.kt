package com.asrul.jffplus.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.asrul.jffplus.R
import com.asrul.jffplus.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
    }

    private var movieId: String? = null
    private var tvShowId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initWindow()
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        movieId = intent.getStringExtra(EXTRA_MOVIE)
        tvShowId = intent.getStringExtra(EXTRA_TV_SHOW)

        movieId?.let {
            bindMovie()
        }
        tvShowId?.let {
            bindTvShow()
        }
    }

    private fun initWindow() {
        val w: Window = window
        w.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun bindTvShow() {
        viewModel.setTvShowId(tvShowId)
        val tvShowDetail = viewModel.getTvShowDetail()

        binding.apply {
            Glide.with(applicationContext)
                .load(tvShowDetail.posterPath)
                .transform(CenterCrop(), RoundedCorners(ROUNDING_RADIUS))
                .into(imagePoster)

            Glide.with(applicationContext)
                .load(tvShowDetail.backdropPath)
                .centerCrop()
                .into(imageBackdrop)

            textTitle.text = tvShowDetail.title
            textLength.text = tvShowDetail.length
            textYear.text = tvShowDetail.year
            textDirector.text = tvShowDetail.director
            textAbout.text = tvShowDetail.about
            textCast.text = tvShowDetail.cast
        }
    }

    private fun bindMovie() {
        viewModel.setMovieId(movieId)
        val movieDetail = viewModel.getDetail()
        binding.apply {
            Glide.with(applicationContext)
                .load(movieDetail.posterPath)
                .transform(CenterCrop(), RoundedCorners(ROUNDING_RADIUS))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                        .error(R.drawable.ic_baseline_broken_image_24)
                )
                .into(imagePoster)
            Glide.with(applicationContext)
                .load(movieDetail.backdropPath)
                .centerCrop()
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                        .error(R.drawable.ic_baseline_broken_image_24)
                )
                .into(imageBackdrop)
            textTitle.text = movieDetail.title
            textLength.text = movieDetail.length
            textYear.text = movieDetail.year
            textDirector.text = movieDetail.director
            textAbout.text = movieDetail.about
            textCast.text = movieDetail.cast
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tvShow"
        const val ROUNDING_RADIUS = 32
    }
}