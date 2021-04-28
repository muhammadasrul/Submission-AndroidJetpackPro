package com.asrul.jffplus.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.asrul.jffplus.LiveDataTest
import com.asrul.jffplus.data.remote.*
import com.asrul.jffplus.data.remote.api.*
import com.asrul.jffplus.data.remote.callback.CallbackGetMovie
import com.asrul.jffplus.data.remote.callback.CallbackGetMovieDetail
import com.asrul.jffplus.data.remote.callback.CallbackGetTvShow
import com.asrul.jffplus.data.remote.callback.CallbackGetTvShowDetail
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class RepositoryTest {

    private val remote = mock(RemoteRepository::class.java)
    private val repository = Repository(remote)
    private val movies = listOf<MovieItem>()
    private val tvShows = listOf<TvItem>()
    private val movieId = "123"
    private val tvShowId = "321"
    private val movieDetail = DetailMovieResponse("title", "path", 123, "overview", 24, "path", "date", 7.5, "status")
    private val tvShowDetail = DetailTvResponse("path", 312, "date", "overview", mutableListOf(), "path", 6.5, "name", mutableListOf())

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

    private fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

    @Test
    fun getMovie() {
        doAnswer {
            (it.arguments[0] as CallbackGetMovie).onSuccess(movies)
            null
        }.`when`(remote).getMovie(this.any())

        val result = LiveDataTest.getValue(repository.getMovie())

        verify(remote, times(1)).getMovie(this.any())
        assertNotNull(result)
    }

    @Test
    fun getTvShow() {
        doAnswer {
            (it.arguments[0] as CallbackGetTvShow).onSuccess(tvShows)
            null
        }.`when`(remote).getTvShow(this.any())

        val result = LiveDataTest.getValue(repository.getTvShow())

        verify(remote, times(1)).getTvShow(this.any())
        assertNotNull(result)
    }

    @Test
    fun getMovieDetail() {
        doAnswer {
            (it.arguments[1] as CallbackGetMovieDetail).onSuccess(movieDetail)
            null
        }.`when`(remote).getMovieDetail(eq(movieId), this.any())

        val result = LiveDataTest.getValue(repository.getMovieDetail(movieId))

        verify(remote, times(1)).getMovieDetail(eq(movieId), this.any())
        assertNotNull(result)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer {
            (it.arguments[1] as CallbackGetTvShowDetail).onSuccess(tvShowDetail)
            null
        }.`when`(remote).getTvShowDetail(eq(tvShowId), this.any())

        val result = LiveDataTest.getValue(repository.getTvShowDetail(tvShowId))

        verify(remote, times(1)).getTvShowDetail(eq(tvShowId), this.any())
        assertNotNull(result)
    }
}