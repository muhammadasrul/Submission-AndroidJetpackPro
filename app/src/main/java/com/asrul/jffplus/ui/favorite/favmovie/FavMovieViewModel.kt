package com.asrul.jffplus.ui.favorite.favmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.vo.Resource

class FavMovieViewModel(private val repository: Repository): ViewModel() {

    private val bait = MutableLiveData<String>()

    fun insertBait() {
        bait.value = "Bait"
    }

    val favMovies: LiveData<Resource<PagedList<MovieEntity>>>? = Transformations.switchMap(bait) {repository.getFavoriteMovies()}
}