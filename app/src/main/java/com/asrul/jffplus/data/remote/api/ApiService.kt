package com.asrul.jffplus.data.remote.api

import com.asrul.jffplus.BuildConfig
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}&sort_by=popularity.desc&with_original_language=ja")
    fun getMovie(): Call<MovieResponse>

    @GET("discover/tv?api_key=${BuildConfig.API_KEY}&sort_by=popularity.desc&with_original_language=ja")
    fun getTvShow(): Call<TvResponse>
}