package com.asrul.jffplus.utils

import com.asrul.jffplus.data.local.entity.MovieEntity

interface MovieItemClickCallbackListener {
    fun onItemClicked(movie: MovieEntity)
}