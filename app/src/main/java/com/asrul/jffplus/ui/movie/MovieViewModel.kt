package com.asrul.jffplus.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.local.Repository
import com.asrul.jffplus.data.remote.api.MovieItem

class MovieViewModel(private val repository: Repository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieItem>> {
        return repository.getMovie()
    }
}