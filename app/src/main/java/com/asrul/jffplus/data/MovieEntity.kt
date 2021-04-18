package com.asrul.jffplus.data

data class MovieEntity(
    var movieId: String,
    var title: String,
    var about: String,
    var director: String,
    var year: String,
    var length: String,
    var cast: String,
    var posterPath: String,
    var backdropPath: String
)