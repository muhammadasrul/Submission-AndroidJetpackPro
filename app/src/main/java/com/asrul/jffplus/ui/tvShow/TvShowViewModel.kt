package com.asrul.jffplus.ui.tvShow

import androidx.lifecycle.ViewModel
import com.asrul.jffplus.data.TvShowEntity
import com.asrul.jffplus.utils.DummyData

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<TvShowEntity> = DummyData.dataTvShow()
}