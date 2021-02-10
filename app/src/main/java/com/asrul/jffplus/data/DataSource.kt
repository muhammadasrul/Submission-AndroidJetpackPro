package com.asrul.jffplus.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.vo.Resource

interface DataSource {
    fun getMovie(): LiveData<Resource<List<MovieEntity>>>
    fun getFavoriteMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun setFavoriteMovieState(movieEntity: MovieEntity, state: Boolean)

    fun getTvShow(): LiveData<Resource<List<TvShowEntity>>>
    fun getFavoriteTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun setFavoriteTvState(tvShowEntity: TvShowEntity, state: Boolean)
}