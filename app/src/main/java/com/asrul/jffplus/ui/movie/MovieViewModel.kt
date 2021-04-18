package com.asrul.jffplus.ui.movie

import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.MovieEntity
import com.asrul.jffplus.utils.DummyData

class MovieViewModel : ViewModel() {
    fun getMovie(): List<MovieEntity> = DummyData.dataMovie()
}