package com.asrul.jffplus.data.remote.callback

import com.asrul.jffplus.data.remote.api.DetailMovieResponse
import retrofit2.Call

interface CallbackGetMovieDetail {
    fun onSuccess(data: DetailMovieResponse)
    fun onFailed(call: Call<DetailMovieResponse>, error: Throwable)
}
