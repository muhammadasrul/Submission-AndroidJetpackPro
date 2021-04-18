package com.asrul.jffplus.ui.detail

import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.MovieEntity
import com.asrul.jffplus.data.TvShowEntity
import com.asrul.jffplus.utils.DummyData

class DetailViewModel: ViewModel() {

    private var movieId: String? = null
    private var tvShowId: String? = null

    private fun getMovie(): ArrayList<MovieEntity> = DummyData.dataMovie()

    fun setMovieId(movieId: String?) {
        this.movieId = movieId
    }

    fun getDetail(): MovieEntity {
        lateinit var result: MovieEntity
        val listMovie = getMovie()
        for (movie in listMovie) {
            if (movie.movieId == movieId) {
                result = movie
            }
        }
        return result
    }

    private fun getTvShow(): ArrayList<TvShowEntity> = DummyData.dataTvShow()

    fun setTvShowId(tvShowId: String?) {
        this.tvShowId = tvShowId
    }

    fun getTvShowDetail(): TvShowEntity {
        lateinit var result: TvShowEntity
        val listTvShow = getTvShow()
        for (tvShow in listTvShow) {
            if (tvShow.tvShowId == tvShowId) {
                result = tvShow
            }
        }
        return result
    }
}