package com.asrul.jffplus.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.asrul.jffplus.PagedListUtil
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.data.remote.*
import com.asrul.jffplus.data.remote.api.*
import com.asrul.jffplus.utils.AppExecutors
import com.asrul.jffplus.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class RepositoryTest {

    private val remoteRepository = mock(RemoteRepository::class.java)
    private val localRepository = mock(LocalRepository::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val repository = Repository(remoteRepository, localRepository, appExecutors)

    private val dataSourceFactory = mock(DataSource.Factory::class.java)

    private val movies = listOf<MovieItem>()
    private val tvShows = listOf<TvItem>()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getFavoriteMovies() {
        `when`(localRepository.getFavoriteMovies()).thenReturn(dataSourceFactory as DataSource.Factory<Int, MovieEntity>)
        repository.getFavoriteMovies()
        val result = Resource.success(PagedListUtil.mockPagedList(movies))
        verify(localRepository).getFavoriteMovies()
        assertNotNull(result.data)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getFavoriteTvShows() {
        `when`(localRepository.getFavoriteTvShow()).thenReturn(dataSourceFactory as DataSource.Factory<Int, TvShowEntity>)
        repository.getFavoriteTvShows()
        val result = Resource.success(PagedListUtil.mockPagedList(tvShows))
        verify(localRepository).getFavoriteTvShow()
        assertNotNull(result.data)
    }
}