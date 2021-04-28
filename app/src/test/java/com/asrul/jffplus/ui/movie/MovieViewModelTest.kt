package com.asrul.jffplus.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.asrul.jffplus.data.local.Repository
import com.asrul.jffplus.data.remote.api.MovieItem
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<MovieItem>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getMovie() {
        val dummy = listOf<MovieItem>()
        val data = MutableLiveData<List<MovieItem>>()
        data.value = dummy

        `when`(repository.getMovie()).thenReturn(data)
        viewModel.getMovie().observeForever(observer)
        verify(repository).getMovie()
        verify(observer).onChanged(dummy)
        assertNotNull(data)
    }
}