package com.asrul.jffplus.data.remote.callback

import com.asrul.jffplus.data.remote.api.MovieItem
import com.asrul.jffplus.data.remote.api.MovieResponse
import retrofit2.Call

interface CallbackGetMovie {
    fun onSuccess(data: List<MovieItem>)
    fun onFailed(call: Call<MovieResponse>, error: Throwable)
}
