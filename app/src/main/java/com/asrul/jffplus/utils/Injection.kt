package com.asrul.jffplus.utils

import com.asrul.jffplus.data.local.Repository
import com.asrul.jffplus.data.remote.RemoteRepository

object Injection {
    fun provideRepository(): Repository {
        val remoteRepository = RemoteRepository()
        return Repository(remoteRepository)
    }
}