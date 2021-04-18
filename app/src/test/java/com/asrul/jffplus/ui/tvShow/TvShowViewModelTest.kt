package com.asrul.jffplus.ui.tvShow

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShow = viewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals(10, tvShow.size)
    }
}