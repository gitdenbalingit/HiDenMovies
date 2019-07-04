package com.hiden.movies.presentation.model

import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.data.entity.PagedResponse

data class MovieDetailView(
    val backdropPath: String?,
    val belongsToCollection: Any?,
    val budget: Int,
    val genres: List<GenreDataView>,
    val homepage: String?,
    val id: Int,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int?,
    val status: String,
    val tagline: String?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val similar: PagedResponse<MovieResponse>
) {
    data class GenreDataView(
        val id: Int,
        val name: String
    )

}