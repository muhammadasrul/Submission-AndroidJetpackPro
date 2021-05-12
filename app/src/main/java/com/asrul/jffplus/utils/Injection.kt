package com.asrul.jffplus.utils

import android.content.Context
import com.asrul.jffplus.data.Repository
import com.asrul.jffplus.data.local.LocalRepository
import com.asrul.jffplus.data.remote.RemoteRepository

object Injection {
    fun provideRepository(context: Context): Repository {
        val remoteRepository = RemoteRepository()
        val localRepository = LocalRepository(context)
        val appExecutors = AppExecutors()
        return Repository(remoteRepository, localRepository, appExecutors)
    }
}