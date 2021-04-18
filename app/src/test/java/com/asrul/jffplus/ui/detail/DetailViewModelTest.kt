package com.asrul.jffplus.ui.detail

import com.asrul.jffplus.utils.DummyData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel
    private val movie = DummyData.dataMovie()[0]
    private val movieId = movie.movieId
    private val tvShow = DummyData.dataTvShow()[0]
    private val tvShowId = tvShow.tvShowId


    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
        detailViewModel.setMovieId(movieId)
        detailViewModel.setTvShowId(tvShowId)
    }

    @Test
    fun getDetail() {
        detailViewModel.setMovieId(movie.movieId)
        val movieDetail = detailViewModel.getDetail()
        assertNotNull(movieDetail)
        assertEquals(movie.movieId, movieDetail.movieId)
        assertEquals(movie.title, movieDetail.title)
        assertEquals(movie.year, movieDetail.year)
        assertEquals(movie.length, movieDetail.length)
        assertEquals(movie.director, movieDetail.director)
        assertEquals(movie.about, movieDetail.about)
        assertEquals(movie.cast, movieDetail.cast)
        assertEquals(movie.posterPath, movieDetail.posterPath)
    }

    @Test
    fun getTvShowDetail() {
        detailViewModel.setTvShowId(tvShow.tvShowId)
        val tvShowDetail = detailViewModel.getTvShowDetail()
        assertNotNull(tvShowDetail)
        assertEquals(tvShow.tvShowId, tvShowDetail.tvShowId)
        assertEquals(tvShow.title, tvShowDetail.title)
        assertEquals(tvShow.year, tvShowDetail.year)
        assertEquals(tvShow.length, tvShowDetail.length)
        assertEquals(tvShow.director, tvShowDetail.director)
        assertEquals(tvShow.about, tvShowDetail.about)
        assertEquals(tvShow.cast, tvShowDetail.cast)
        assertEquals(tvShow.posterPath, tvShowDetail.posterPath)
    }
}