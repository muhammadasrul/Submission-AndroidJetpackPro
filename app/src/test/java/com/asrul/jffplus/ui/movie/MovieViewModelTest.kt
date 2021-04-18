package com.asrul.jffplus.ui.movie

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(10, movie.size)
    }
}