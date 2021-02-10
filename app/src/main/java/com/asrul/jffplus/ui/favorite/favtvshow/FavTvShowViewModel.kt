package com.asrul.jffplus.ui.favorite.favtvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.Repository

class FavTvShowViewModel(private val repository: Repository): ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "Bait"
    }

    val favTvShow = Transformations.switchMap(bait) {repository.getFavoriteTvShows()}
}