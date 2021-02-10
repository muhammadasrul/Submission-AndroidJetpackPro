package com.asrul.jffplus.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.vo.Resource
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
    private lateinit var observer: Observer<Resource<List<TvShowEntity>>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
    }

    @Test
    fun getTvShow() {
        val dummy = Resource.success(listOf<TvShowEntity>())
        val data = MutableLiveData<Resource<List<TvShowEntity>>>()
        data.value = dummy

        `when`(repository.getTvShow()).thenReturn(data)
        viewModel.getTvShow().observeForever(observer)
        verify(repository).getTvShow()
        verify(observer).onChanged(dummy)
        assertNotNull(data)
    }
}