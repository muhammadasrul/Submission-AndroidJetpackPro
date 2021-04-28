package com.asrul.jffplus.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.asrul.jffplus.data.local.Repository
import com.asrul.jffplus.data.remote.api.DetailMovieResponse
import com.asrul.jffplus.data.remote.api.DetailTvResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var movieDummy: DetailMovieResponse
    private val movieId = "123"
    private lateinit var tvShowDummy: DetailTvResponse
    private val tvShowId = "321"
    private val observer = mock(Observer::class.java)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailViewModel = DetailViewModel(repository)

        movieDummy = DetailMovieResponse(
            "title",
            "path",
            123,
            "overview",
            24,
            "path",
            "date",
            6.3,
            "status"
        )

        tvShowDummy = DetailTvResponse(
            "path",
            321,
            "date",
            "overview",
            mutableListOf(),
            "path",
            7.5,
            "name",
            mutableListOf(),
        )
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getDetail() {
        val movie = MutableLiveData<DetailMovieResponse>()
        movie.value = movieDummy

        `when`(repository.getMovieDetail(movieId)).thenReturn(movie)
        detailViewModel.getMovieDetail(movieId).observeForever(observer as Observer<DetailMovieResponse>)

        verify(observer).onChanged(movieDummy)
        assertNotNull(movie)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<DetailTvResponse>()
        tvShow.value = tvShowDummy

        `when`(repository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        detailViewModel.getTvShowDetail(tvShowId).observeForever(observer as Observer<DetailTvResponse>)

        verify(observer).onChanged(tvShowDummy)
        assertNotNull(tvShow)
    }
}