package com.asrul.jffplus.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.data.local.room.RoomDB
import com.asrul.jffplus.data.local.room.RoomDao

class LocalRepository(context: Context) {

    private val roomDao: RoomDao = RoomDB.getDatabaseInstance(context).roomDao()

    fun getMovies(): LiveData<List<MovieEntity>> = roomDao.getMovie()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = roomDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = roomDao.insertMovie(movies)

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.favorite = state
        roomDao.updateMovie(movie)
    }

    fun getTvShow(): LiveData<List<TvShowEntity>> = roomDao.getTvShow()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = roomDao.getFavoriteTvShow()

    fun insertTvShow(tvShows: List<TvShowEntity>) = roomDao.insertTvShow(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        tvShow.favorite = state
        roomDao.updateTvShow(tvShow)
    }
}