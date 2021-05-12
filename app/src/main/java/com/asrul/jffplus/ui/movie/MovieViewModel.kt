package com.asrul.jffplus.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.vo.Resource

class MovieViewModel(private val repository: Repository) : ViewModel() {
    fun getMovie(): LiveData<Resource<List<MovieEntity>>> {
        return repository.getMovie()
    }
}