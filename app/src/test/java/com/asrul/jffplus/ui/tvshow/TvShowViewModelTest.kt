package com.asrul.jffplus.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.asrul.jffplus.data.local.Repository
import com.asrul.jffplus.data.remote.api.TvItem
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<TvItem>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
    }

    @Test
    fun getTvShow() {
        val dummy = listOf<TvItem>()
        val data = MutableLiveData<List<TvItem>>()
        data.value = dummy

        `when`(repository.getTvShow()).thenReturn(data)
        viewModel.getTvShow().observeForever(observer)
        verify(repository).getTvShow()
        verify(observer).onChanged(dummy)
        assertNotNull(data)
    }
}