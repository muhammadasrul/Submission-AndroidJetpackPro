package com.asrul.jffplus.ui.favorite.favmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.Repository
class FavMovieViewModel(private val repository: Repository): ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "Bait"
    }

    val favMovies = Transformations.switchMap(bait) {repository.getFavoriteMovies()}
}