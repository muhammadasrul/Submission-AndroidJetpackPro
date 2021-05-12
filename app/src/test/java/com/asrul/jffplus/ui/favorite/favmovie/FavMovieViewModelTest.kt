package com.asrul.jffplus.ui.favorite.favmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.asrul.jffplus.LiveDataTest
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class FavMovieViewModelTest {

    private lateinit var favMovieViewModel: FavMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieEntity>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favMovieViewModel = FavMovieViewModel(repository)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getFavMovies() {
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val pagedList = mock(PagedList::class.java) as PagedList<MovieEntity>
        movie.value = Resource.success(pagedList)

        `when`(repository.getFavoriteMovies()).thenReturn(movie)
        favMovieViewModel.insertBait()
        favMovieViewModel.favMovies?.observeForever(observer as Observer<in Resource<PagedList<MovieEntity>>>)
        val result = LiveDataTest.getValue(favMovieViewModel.favMovies)
        verify(observer).onChanged(Resource.success(pagedList))
        assertNotNull(result.data)
    }
}