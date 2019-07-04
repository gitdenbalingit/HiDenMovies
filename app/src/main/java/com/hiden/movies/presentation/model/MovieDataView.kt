package com.hiden.movies.presentation.model


data class MovieDataView(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)