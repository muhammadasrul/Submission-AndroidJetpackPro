package com.asrul.jffplus.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity

class DetailViewModel(private val repository: Repository): ViewModel() {
    var movieDetail = MutableLiveData<MovieEntity>()
    var tvShowDetail = MutableLiveData<TvShowEntity>()

    fun setMovieFavoriteState(movieEntity: MovieEntity, state: Boolean) {
        repository.setFavoriteMovieState(movieEntity, state)
    }

    fun setTvShowFavoriteState(tvShowEntity: TvShowEntity, state: Boolean) {
        repository.setFavoriteTvState(tvShowEntity, state)
    }
}