package com.asrul.jffplus.ui.favorite.favtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.vo.Resource

class FavTvShowViewModel(private val repository: Repository): ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "Bait"
    }

    val favTvShow: LiveData<Resource<PagedList<TvShowEntity>>>? = Transformations.switchMap(bait) {repository.getFavoriteTvShows()}
}