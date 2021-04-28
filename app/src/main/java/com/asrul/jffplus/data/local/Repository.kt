package com.asrul.jffplus.data.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asrul.jffplus.data.remote.*
import com.asrul.jffplus.data.remote.api.*
import com.asrul.jffplus.data.remote.callback.CallbackGetMovie
import com.asrul.jffplus.data.remote.callback.CallbackGetMovieDetail
import com.asrul.jffplus.data.remote.callback.CallbackGetTvShow
import com.asrul.jffplus.data.remote.callback.CallbackGetTvShowDetail
import retrofit2.Call

class Repository(private val remoteRepository: RemoteRepository): DataSource {

    val movie = MutableLiveData<List<MovieItem>>()
    val movieDetail = MutableLiveData<DetailMovieResponse>()
    val tvShow = MutableLiveData<List<TvItem>>()
    val tvShowDetail = MutableLiveData<DetailTvResponse>()

    override fun getMovie(): LiveData<List<MovieItem>> {
        remoteRepository.getMovie(object : CallbackGetMovie {
            override fun onSuccess(data: List<MovieItem>) {
                movie.postValue(data)
            }

            override fun onFailed(call: Call<MovieResponse>, error: Throwable) {
                Log.d("onFailed: ", error.printStackTrace().toString())
            }
        })
        return movie
    }

    override fun getMovieDetail(id: String): LiveData<DetailMovieResponse> {
        remoteRepository.getMovieDetail(id, object : CallbackGetMovieDetail {
            override fun onSuccess(data: DetailMovieResponse) {
                movieDetail.postValue(data)
            }

            override fun onFailed(call: Call<DetailMovieResponse>, error: Throwable) {
                Log.d("onFailed: ", error.printStackTrace().toString())
            }
        })
        return movieDetail
    }

    override fun getTvShow(): LiveData<List<TvItem>> {
        remoteRepository.getTvShow(object : CallbackGetTvShow {
            override fun onSuccess(data: List<TvItem>) {
                tvShow.postValue(data)
            }

            override fun onFailed(call: Call<TvResponse>, error: Throwable) {
                Log.d("onFailed: ", error.printStackTrace().toString())
            }
        })
        return tvShow
    }

    override fun getTvShowDetail(id: String): LiveData<DetailTvResponse> {
        remoteRepository.getTvShowDetail(id, object : CallbackGetTvShowDetail {
            override fun onSuccess(data: DetailTvResponse) {
                tvShowDetail.postValue(data)
            }

            override fun onFailed(call: Call<DetailTvResponse>, error: Throwable) {
                Log.d("onFailed: ", error.printStackTrace().toString())
            }
        })
        return tvShowDetail
    }


}