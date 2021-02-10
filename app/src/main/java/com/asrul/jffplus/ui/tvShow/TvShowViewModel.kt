package com.asrul.jffplus.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.vo.Resource

class TvShowViewModel(private val repository: Repository) : ViewModel() {
    fun getTvShow(): LiveData<Resource<List<TvShowEntity>>> {
        return repository.getTvShow()
    }
}