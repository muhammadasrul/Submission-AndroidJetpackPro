package com.asrul.jffplus.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asrul.jffplus.data.local.Repository
import com.asrul.jffplus.ui.detail.DetailViewModel
import com.asrul.jffplus.ui.movie.MovieViewModel
import com.asrul.jffplus.ui.tvshow.TvShowViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    companion object {
        private lateinit var instance: ViewModelFactory
        fun getInstance(): ViewModelFactory {
            synchronized(ViewModelFactory::class.java) {
                instance = ViewModelFactory(Injection.provideRepository())
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
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}