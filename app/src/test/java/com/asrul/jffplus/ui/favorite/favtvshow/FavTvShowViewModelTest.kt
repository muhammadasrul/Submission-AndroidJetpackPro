package com.asrul.jffplus.ui.favorite.favtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.asrul.jffplus.LiveDataTest
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class FavTvShowViewModelTest {

    private lateinit var favTvShowViewModel: FavTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<List<TvShowEntity>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favTvShowViewModel = FavTvShowViewModel(repository)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getFavTvShow() {
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<TvShowEntity>
        tvShow.value = Resource.success(pagedList)

        `when`(repository.getFavoriteTvShows()).thenReturn(tvShow)
        favTvShowViewModel.insertBait()
        favTvShowViewModel.favTvShow?.observeForever(observer as Observer<in Resource<PagedList<TvShowEntity>>>)
        val result = LiveDataTest.getValue(favTvShowViewModel.favTvShow)
        verify(observer).onChanged(Resource.success(pagedList))
        assertNotNull(result.data)
    }
}