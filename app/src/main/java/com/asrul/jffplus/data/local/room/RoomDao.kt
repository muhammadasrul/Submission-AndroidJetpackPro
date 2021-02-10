package com.asrul.jffplus.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.utils.Maps

@Dao
interface RoomDao {

    @Query("SELECT * FROM ${Maps.movieTable}")
    fun getMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM ${Maps.movieTable} WHERE ${Maps.favorite} = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(data: List<MovieEntity>)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateMovie(data: MovieEntity)

    @Query("SELECT * FROM ${Maps.tvShowTable}")
    fun getTvShow(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM ${Maps.tvShowTable} WHERE ${Maps.favorite} = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(data: List<TvShowEntity>)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateTvShow(data: TvShowEntity)
}