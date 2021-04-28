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
import com.asrul.jffplus.databinding.ActivityDetailBinding
import com.asrul.jffplus.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: DetailViewModel

    private var movieId: String? = null
    private var tvShowId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initWindow()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        movieId = intent.getStringExtra(EXTRA_MOVIE)
        tvShowId = intent.getStringExtra(EXTRA_TV_SHOW)

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
        binding.tvDirector.text = resources.getString(R.string.creator)
        tvShowId?.let {
            viewModel.getTvShowDetail(it).observe(this, { tvShowDetail ->
                binding.apply {
                    progressBar.visibility = View.GONE
                    imagePoster.load(BuildConfig.POSTER_URL+tvShowDetail.posterPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                        transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                    }

                    imageBackdrop.load(BuildConfig.POSTER_URL+tvShowDetail.posterPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                    }

                    textTitle.text = tvShowDetail.name

                    val runtime =
                        if (tvShowDetail.episodeRunTime.size > 1) {
                            val min = tvShowDetail.episodeRunTime[0].toString()
                            val max = tvShowDetail.episodeRunTime[1].toString()
                            resources.getString(R.string.ep_runtime, min, max)
                        } else {
                            resources.getString(R.string.runtime, tvShowDetail.episodeRunTime[0])
                        }
                    textLength.text = runtime
                    textYear.text = tvShowDetail.firstAirDate
                    if (tvShowDetail.createdBy.isNotEmpty()) {
                        textDirector.text = tvShowDetail.createdBy[0].name
                    } else {
                        textDirector.text = "-"
                    }
                    textAbout.text = tvShowDetail.overview
                    val rating = tvShowDetail.voteAverage.div(VOTE_AVERAGE_DIV)
                    ratingBar.rating = rating.toFloat()
                }
            })
        }
    }

    private fun bindMovie() {
        binding.tvDirector.text = resources.getString(R.string.status)
        movieId?.let {
            viewModel.getMovieDetail(it).observe(this, { movieDetail ->
                binding.apply {
                    progressBar.visibility = View.GONE
                    imagePoster.load(BuildConfig.POSTER_URL+movieDetail.posterPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                        transformations(RoundedCornersTransformation(ROUNDING_RADIUS))
                    }

                    imageBackdrop.load(BuildConfig.POSTER_URL+movieDetail.posterPath) {
                        placeholder(R.drawable.ic_baseline_refresh_24)
                        error(R.drawable.ic_baseline_broken_image_24)
                    }

                    textTitle.text = movieDetail.title
                    textLength.text = resources.getString(R.string.runtime, movieDetail.runtime)
                    textYear.text = movieDetail.releaseDate
                    textDirector.text = movieDetail.status
                    textAbout.text = movieDetail.overview
                    val rating = movieDetail.voteAverage.div(VOTE_AVERAGE_DIV)
                    ratingBar.rating = rating.toFloat()
                }
            })
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
        const val ROUNDING_RADIUS = 32f
        const val VOTE_AVERAGE_DIV = 2
    }
}