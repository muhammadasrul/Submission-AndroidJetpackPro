package com.asrul.jffplus.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.RoundedCornersTransformation
import com.asrul.jffplus.BuildConfig
import com.asrul.jffplus.R
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.databinding.ActivityDetailBinding
import com.asrul.jffplus.utils.ViewModelFactory
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: DetailViewModel

    private var movieDetail: MovieEntity? = null
    private var tvShowDetail: TvShowEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initWindow()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        val factory = ViewModelFactory.getInstance(applicationContext)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        movieDetail = intent.getParcelableExtra(EXTRA_MOVIE)
        tvShowDetail = intent.getParcelableExtra(EXTRA_TV_SHOW)

        bindMovie()
        bindTvShow()
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
        tvShowDetail?.let {
            viewModel.tvShowDetail.value = tvShowDetail
            viewModel.tvShowDetail.observe(this, { tvShow ->
                binding.apply {
                    progressBar.visibility = View.GONE
                    imagePoster.load(BuildConfig.POSTER_URL+tvShow.posterPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                        transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                    }
                    imageBackdrop.load(BuildConfig.POSTER_URL+tvShow.backdropPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                    }
                    textTitle.text = tvShow.name
                    textRelease.text = tvShow.firstAirDate
                    textPopularity.text = tvShow.popularity
                    textVcount.text = tvShow.voteCount
                    textAbout.text = tvShow.overview
                    val rating = tvShow.voteAverage.div(VOTE_AVERAGE_DIV)
                    ratingBar.rating = rating.toFloat()
                    if (tvShow.favorite) {
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                    } else {
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    }
                }
            })
            binding.apply {
                btnFav.setOnClickListener {
                    if (tvShowDetail?.favorite == true) {
                        tvShowDetail?.let { viewModel.setTvShowFavoriteState(it, false) }
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        toast(resources.getString(R.string.remove_from_favorite))
                    } else {
                        tvShowDetail?.let { viewModel.setTvShowFavoriteState(it, true) }
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                        toast(resources.getString(R.string.add_to_favorite))
                    }
                }

                if (tvShowDetail?.favorite == true) {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }

    private fun bindMovie() {
        movieDetail?.let {
            viewModel.movieDetail.value = movieDetail
            viewModel.movieDetail.observe(this, { movie ->
                binding.apply {
                    progressBar.visibility = View.GONE
                    imagePoster.load(BuildConfig.POSTER_URL+movieDetail?.posterPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                        transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                    }

                    imageBackdrop.load(BuildConfig.POSTER_URL+movieDetail?.backdropPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)

                    }

                    textTitle.text = movie.title
                    textRelease.text = movie.releaseDate
                    textPopularity.text = movie.popularity
                    textVcount.text = movie.voteCount
                    textAbout.text = movie.overview
                    val rating = movie.voteAverage.div(VOTE_AVERAGE_DIV)
                    ratingBar.rating = rating.toFloat()
                }
            })

            binding.apply {
                btnFav.setOnClickListener {
                    if (movieDetail?.favorite == true) {
                        movieDetail?.let { viewModel.setMovieFavoriteState(it, false) }
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        toast(resources.getString(R.string.remove_from_favorite))
                    } else {
                        movieDetail?.let { viewModel.setMovieFavoriteState(it, true) }
                        btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                        toast(resources.getString(R.string.add_to_favorite))
                    }
                }

                if (movieDetail?.favorite == true) {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }
            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}