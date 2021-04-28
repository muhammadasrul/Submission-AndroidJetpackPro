package com.asrul.jffplus.data.remote

import com.asrul.jffplus.utils.EspressoIdlingResource
import com.asrul.jffplus.data.remote.api.*
import com.asrul.jffplus.data.remote.callback.CallbackGetMovie
import com.asrul.jffplus.data.remote.callback.CallbackGetMovieDetail
import com.asrul.jffplus.data.remote.callback.CallbackGetTvShow
import com.asrul.jffplus.data.remote.callback.CallbackGetTvShowDetail
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    fun getMovie(callbackGetMovie: CallbackGetMovie) {
        EspressoIdlingResource.increment()
        doAsync {
            ApiConfig.getApiService().getMovie()
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            val data = response.body()
                            val movie = mutableListOf<MovieItem>()
                            data?.let { movie.addAll(it.results) }
                            callbackGetMovie.onSuccess(movie)
                            EspressoIdlingResource.decrement()
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        callbackGetMovie.onFailed(call, t)
                    }
                })
        }
    }

    fun getMovieDetail(id: String, callbackGetGetMovieDetail: CallbackGetMovieDetail) {
        EspressoIdlingResource.increment()
        doAsync {
            ApiConfig.getApiService().getMovieDetail(id)
                .enqueue(object : Callback<DetailMovieResponse> {
                    override fun onResponse(
                        call: Call<DetailMovieResponse>,
                        response: Response<DetailMovieResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            val data = response.body()
                            data?.let { callbackGetGetMovieDetail.onSuccess(it) }
                            EspressoIdlingResource.decrement()
                        }
                    }

                    override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                        callbackGetGetMovieDetail.onFailed(call, t)
                    }
                })
        }
    }

    fun getTvShow(callbackGetTvShow: CallbackGetTvShow) {
        EspressoIdlingResource.increment()
        doAsync {
            ApiConfig.getApiService().getTvShow()
                .enqueue(object : Callback<TvResponse> {
                    override fun onResponse(
                        call: Call<TvResponse>,
                        response: Response<TvResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            val data = response.body()
                            val tvShow = mutableListOf<TvItem>()
                            data?.let { tvShow.addAll(it.results) }
                            callbackGetTvShow.onSuccess(tvShow)
                            EspressoIdlingResource.decrement()
                        }
                    }

                    override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                        callbackGetTvShow.onFailed(call, t)
                    }
                })
        }
    }

    fun getTvShowDetail(id: String, callbackGetTvShowDetail: CallbackGetTvShowDetail) {
        EspressoIdlingResource.increment()
        doAsync {
            ApiConfig.getApiService().getTvShowDetail(id)
                .enqueue(object : Callback<DetailTvResponse> {
                    override fun onResponse(
                        call: Call<DetailTvResponse>,
                        response: Response<DetailTvResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            val data = response.body()
                            data?.let { callbackGetTvShowDetail.onSuccess(it) }
                            EspressoIdlingResource.decrement()
                        }
                    }

                    override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                        callbackGetTvShowDetail.onFailed(call, t)
                    }
                })
        }
    }
}