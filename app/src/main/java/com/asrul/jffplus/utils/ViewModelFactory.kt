package com.asrul.jffplus.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.ui.detail.DetailViewModel
import com.asrul.jffplus.ui.favorite.favmovie.FavMovieViewModel
import com.asrul.jffplus.ui.favorite.favtvshow.FavTvShowViewModel
import com.asrul.jffplus.ui.movie.MovieViewModel
import com.asrul.jffplus.ui.tvShow.TvShowViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        private lateinit var instance: ViewModelFactory
        fun getInstance(context: Context): ViewModelFactory {
            synchronized(ViewModelFactory::class.java) {
                instance = ViewModelFactory(Injection.provideRepository(context))
            }
            return instance
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            MovieViewModel::class.java -> {
                return MovieViewModel(repository) as T
            }
            TvShowViewModel::class.java -> {
                return TvShowViewModel(repository) as T
            }
            DetailViewModel::class.java -> {
                return DetailViewModel(repository) as T
            }
            FavMovieViewModel::class.java -> {
                return FavMovieViewModel(repository) as T
            }
            FavTvShowViewModel::class.java -> {
                return FavTvShowViewModel(repository) as T
            }
        }

        throw IllegalArgumentException("Unknow ViewModel class: " + modelClass.name)
    }
}