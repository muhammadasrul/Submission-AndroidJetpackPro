package com.asrul.jffplus.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private var movieDetail = MovieEntity("1", "title", "releaseDate", "popularity", "voteCount", "posterPath", "backdropPath", "overview", 12.2, false)
    private var tvShowDetail = TvShowEntity("1", "title", "releaseDate", "popularity", "voteCount", "posterPath", "backdropPath", "overview", 12.2, false)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun setMovieFavoriteState() {
        doNothing().`when`(repository).setFavoriteMovieState(movieDetail, false)
        viewModel.setMovieFavoriteState(movieDetail, true)

        verify(repository, times(1)).setFavoriteMovieState(movieDetail, true)
    }

    @Test
    fun setTvShowFavoriteState() {
        doNothing().`when`(repository).setFavoriteTvState(tvShowDetail, false)
        viewModel.setTvShowFavoriteState(tvShowDetail, true)

        verify(repository, times(1)).setFavoriteTvState(tvShowDetail, true)
    }
}