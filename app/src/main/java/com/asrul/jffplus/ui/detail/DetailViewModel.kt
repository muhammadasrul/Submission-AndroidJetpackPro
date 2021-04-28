package com.asrul.jffplus.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.local.Repository
import com.asrul.jffplus.data.remote.api.DetailMovieResponse
import com.asrul.jffplus.data.remote.api.DetailTvResponse

class DetailViewModel(private val repository: Repository): ViewModel() {

    fun getMovieDetail(id: String): LiveData<DetailMovieResponse> {
        return repository.getMovieDetail(id)
    }

    fun getTvShowDetail(id: String): LiveData<DetailTvResponse> {
        return repository.getTvShowDetail(id)
    }
}