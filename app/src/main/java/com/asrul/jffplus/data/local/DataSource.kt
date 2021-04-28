package com.asrul.jffplus.data.local

import androidx.lifecycle.LiveData
import com.asrul.jffplus.data.remote.api.DetailMovieResponse
import com.asrul.jffplus.data.remote.api.DetailTvResponse
import com.asrul.jffplus.data.remote.api.MovieItem
import com.asrul.jffplus.data.remote.api.TvItem

interface DataSource {
    fun getMovie(): LiveData<List<MovieItem>>
    fun getMovieDetail(id: String): LiveData<DetailMovieResponse>
    fun getTvShow(): LiveData<List<TvItem>>
    fun getTvShowDetail(id: String): LiveData<DetailTvResponse>
}