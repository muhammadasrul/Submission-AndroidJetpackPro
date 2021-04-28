package com.asrul.jffplus.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.local.Repository
import com.asrul.jffplus.data.remote.api.TvItem

class TvShowViewModel(private val repository: Repository) : ViewModel() {
    fun getTvShow(): LiveData<List<TvItem>> {
        return repository.getTvShow()
    }
}