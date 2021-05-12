package com.asrul.jffplus.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asrul.jffplus.utils.Maps
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = Maps.tvShowTable)
data class TvShowEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Maps.id)
    var id: String,

    @NotNull
    @ColumnInfo(name = Maps.tvName)
    var name: String,

    @NonNull
    @ColumnInfo(name = Maps.tvAirDate)
    var firstAirDate: String,

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
