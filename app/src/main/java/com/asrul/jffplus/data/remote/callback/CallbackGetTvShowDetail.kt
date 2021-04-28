package com.asrul.jffplus.data.remote.callback

import com.asrul.jffplus.data.remote.api.DetailTvResponse
import retrofit2.Call

interface CallbackGetTvShowDetail {
    fun onSuccess(data: DetailTvResponse)
    fun onFailed(call: Call<DetailTvResponse>, error: Throwable)
}