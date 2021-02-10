package com.asrul.jffplus.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.asrul.jffplus.data.local.entity.MovieEntity
import com.asrul.jffplus.data.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {
    abstract fun roomDao() : RoomDao

    companion object {
        private const val dbName = "jff.db"

        @Volatile
        private lateinit var INSTANCE: RoomDB

        fun getDatabaseInstance(context: Context): RoomDB {
            synchronized(RoomDB::class.java) {
                INSTANCE = Room.databaseBuilder(context, RoomDB::class.java, dbName).build()
            }
            return INSTANCE
        }
    }
}