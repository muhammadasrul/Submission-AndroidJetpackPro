package com.asrul.jffplus.utils

import com.asrul.jffplus.data.local.entity.TvShowEntity

interface TvShowItemClickCallbackListener {
    fun onItemClicked(tvShow: TvShowEntity)
}