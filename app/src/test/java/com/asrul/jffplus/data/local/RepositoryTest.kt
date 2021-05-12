package com.asrul.jffplus.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.asrul.jffplus.LiveDataTest
import com.asrul.jffplus.PagedListUtil
import com.asrul.jffplus.TestExecutor
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.data.remote.RemoteRepository
import com.asrul.jffplus.data.remote.api.MovieItem
import com.asrul.jffplus.data.remote.api.TvItem
import com.asrul.jffplus.ui.movie.MovieViewModel
import com.asrul.jffplus.utils.AppExecutors
import com.asrul.jffplus.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
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

    private val testExecutor = AppExecutors(TestExecutor(), TestExecutor(), TestExecutor())

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(repository)
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

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var repositoryMock: Repository

    @Test
    fun getMovie() {
        val dummy = Resource.success(listOf<MovieEntity>())
        val data = MutableLiveData<Resource<List<MovieEntity>>>()
        data.value = dummy

        `when`(repositoryMock.getMovie()).thenReturn(data)
        repositoryMock.getMovie()
        val result = Resource.success(LiveDataTest)
        verify(repositoryMock).getMovie()
        assertNotNull(result.data)
    }

    @Test
    fun getTvShow() {
        val dummy = Resource.success(listOf<TvShowEntity>())
        val data = MutableLiveData<Resource<List<TvShowEntity>>>()
        data.value = dummy

        `when`(repositoryMock.getTvShow()).thenReturn(data)
        repositoryMock.getTvShow()
        val result = Resource.success(LiveDataTest)
        verify(repositoryMock).getTvShow()
        assertNotNull(result.data)
    }

    @Test
    fun setMovieFavoriteState() {
        val movieDetail = MovieEntity("1", "title", "releaseDate", "popularity", "voteCount", "posterPath", "backdropPath", "overview", 12.2, false)
        `when`(appExecutors.diskIO()).thenReturn(testExecutor.diskIO())
        doNothing().`when`(localRepository).setFavoriteMovie(movieDetail, true)
        repository.setFavoriteMovieState(movieDetail, true)

        verify(localRepository, times(1)).setFavoriteMovie(movieDetail, true)
    }

    @Test
    fun setTvShowFavoriteState() {
        val tvShowDetail = TvShowEntity("1", "title", "releaseDate", "popularity", "voteCount", "posterPath", "backdropPath", "overview", 12.2, false)
        `when`(appExecutors.diskIO()).thenReturn(testExecutor.diskIO())
        doNothing().`when`(localRepository).setFavoriteTvShow(tvShowDetail, true)
        repository.setFavoriteTvState(tvShowDetail, true)

        verify(localRepository, times(1)).setFavoriteTvShow(tvShowDetail, true)
    }
}