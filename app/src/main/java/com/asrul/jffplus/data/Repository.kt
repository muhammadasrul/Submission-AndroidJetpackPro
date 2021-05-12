package com.asrul.jffplus.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.asrul.jffplus.data.local.LocalRepository
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity
import com.asrul.jffplus.data.remote.*
import com.asrul.jffplus.data.remote.api.*
import com.asrul.jffplus.utils.AppExecutors
import com.asrul.jffplus.vo.Resource

class Repository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appExecutors: AppExecutors
): DataSource {

    override fun getMovie(): LiveData<Resource<List<MovieEntity>>> {

        return object: NetworkBoundResource<List<MovieEntity>, List<MovieItem>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<MovieEntity>> = localRepository.getMovies()

            override fun shouldFatch(data: List<MovieEntity>): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> = remoteRepository.getMovie()

            override fun saveCallResult(data: List<MovieItem>) {
                val movie = mutableListOf<MovieEntity>()
                for (i in data.indices) {
                    movie.add(
                        MovieEntity(
                            data[i].id.toString(),
                            data[i].title,
                            data[i].releaseDate,
                            data[i].popularity.toString(),
                            data[i].voteCount.toString(),
                            data[i].posterPath,
                            data[i].backdropPath,
                            data[i].overview,
                            data[i].voteAverage
                        )
                    )
                }
                localRepository.insertMovies(movie)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieItem>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(localRepository.getFavoriteMovies(), PAGE_SIZE).build()
            }

            override fun shouldFatch(data: PagedList<MovieEntity>): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> = MutableLiveData()

            override fun saveCallResult(data: List<MovieItem>) {

            }
        }.asLiveData()
    }

    override fun setFavoriteMovieState(movieEntity: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localRepository.setFavoriteMovie(movieEntity, state)
        }
    }

    override fun getTvShow(): LiveData<Resource<List<TvShowEntity>>> {

        return object: NetworkBoundResource<List<TvShowEntity>, List<TvItem>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<TvShowEntity>> = localRepository.getTvShow()

            override fun shouldFatch(data: List<TvShowEntity>): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvItem>>> = remoteRepository.getTvShow()

            override fun saveCallResult(data: List<TvItem>) {
                val tvShow = mutableListOf<TvShowEntity>()
                for (i in data.indices) {
                    tvShow.add(
                        TvShowEntity(
                            data[i].id.toString(),
                            data[i].name,
                            data[i].firstAirDate,
                            data[i].popularity.toString(),
                            data[i].voteCount.toString(),
                            data[i].posterPath,
                            data[i].backdropPath,
                            data[i].overview,
                            data[i].voteAverage
                        )
                    )
                }
                localRepository.insertTvShow(tvShow)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvItem>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                return LivePagedListBuilder(localRepository.getFavoriteTvShow(), PAGE_SIZE).build()
            }

            override fun shouldFatch(data: PagedList<TvShowEntity>): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<TvItem>>> = MutableLiveData()

            override fun saveCallResult(data: List<TvItem>) {

            }
        }.asLiveData()
    }

    override fun setFavoriteTvState(tvShowEntity: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localRepository.setFavoriteTvShow(tvShowEntity, state)
        }
    }

    companion object {
        const val PAGE_SIZE = 5
    }
}