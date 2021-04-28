package com.asrul.jffplus.data.remote.callback

import com.asrul.jffplus.data.remote.api.TvItem
import com.asrul.jffplus.data.remote.api.TvResponse
import retrofit2.Call

interface CallbackGetTvShow {
    fun onSuccess(data: List<TvItem>)
    fun onFailed(call: Call<TvResponse>, error: Throwable)
}