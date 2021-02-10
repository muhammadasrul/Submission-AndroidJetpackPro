package com.asrul.jffplus.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asrul.jffplus.utils.Maps
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = Maps.movieTable)
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Maps.id)
    var id: String,

    @NotNull
    @ColumnInfo(name = Maps.movieTitle)
    var title: String,

    @NonNull
    @ColumnInfo(name = Maps.movieRelease)
    var releaseDate: String,

    @NonNull
    @ColumnInfo(name = Maps.popularity)
    var popularity: String,

    @NonNull
    @ColumnInfo(name = Maps.voteCount)
    var voteCount: String,

    @NonNull
    @ColumnInfo(name = Maps.posterPath)
    var posterPath: String,

    @NonNull
    @ColumnInfo(name = Maps.backdropPath)
    var backdropPath: String,

    @NonNull
    @ColumnInfo(name = Maps.overview)
    var overview: String,

    @NonNull
    @ColumnInfo(name = Maps.voteAverage)
    var voteAverage: Double,

    @NonNull
    @ColumnInfo(name = Maps.favorite)
    var favorite: Boolean = false
) : Parcelable
