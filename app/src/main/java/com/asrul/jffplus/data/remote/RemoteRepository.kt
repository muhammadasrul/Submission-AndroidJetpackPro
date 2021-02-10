package com.asrul.jffplus.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.asrul.jffplus.utils.EspressoIdlingResource
import com.asrul.jffplus.data.remote.api.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    fun getMovie() : LiveData<ApiResponse<List<MovieItem>>> {
        val movieLiveData = MutableLiveData<ApiResponse<List<MovieItem>>>()
        EspressoIdlingResource.increment()
        doAsync {
            ApiConfig.getApiService().getMovie()
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                val data = response.body()!!
                                val movie = mutableListOf<MovieItem>()

                                movie.addAll(data.results)

                                movieLiveData.value = ApiResponse.success(movie)
                                EspressoIdlingResource.decrement()
                            }
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
        }
        return movieLiveData
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvItem>>> {
        val tvShowLiveData = MutableLiveData<ApiResponse<List<TvItem>>>()
        EspressoIdlingResource.increment()
        doAsync {
            ApiConfig.getApiService().getTvShow()
                .enqueue(object : Callback<TvResponse> {
                    override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                val data = response.body()!!
                                val tvShow = mutableListOf<TvItem>()

                                tvShow.addAll(data.results)

                                tvShowLiveData.value = ApiResponse.success(tvShow)

                                EspressoIdlingResource.decrement()
                            }
                        }
                    }

                    override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
        }
        return tvShowLiveData
    }
}