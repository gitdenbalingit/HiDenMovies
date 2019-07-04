package com.hiden.movies.data.entity.Mapper

import com.hiden.movies.data.entity.MovieDetailResponse
import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.presentation.model.MovieDataView
import com.hiden.movies.presentation.model.MovieDetailView


/*******************************
 *
 * Movie Entity
 *
 */

fun MovieResponse.toDataView(): MovieDataView = MovieDataView(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun MovieDetailResponse.toDataView(): MovieDetailView = MovieDetailView(
    backdropPath = backdropPath,
    belongsToCollection = belongsToCollection,
    budget = budget,
    genres = this.genres.map { it.toDataView() },
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount,
    similar = similar
)

fun MovieDetailResponse.Genre.toDataView(): MovieDetailView.GenreDataView = MovieDetailView.GenreDataView(
    name = name,
    id = id
)