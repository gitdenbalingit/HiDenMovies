package com.hiden.movies.data.source.repository

import com.hiden.movies.data.entity.MovieDetailResponse
import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.data.entity.PagedResponse
import com.hiden.movies.data.entity.TVShowResponse
import com.hiden.movies.data.source.remote.RemoteMoviesDataSource
import io.reactivex.Single
import javax.inject.Inject

interface MoviesRepository {

    fun searchMovie(query: String): Single<PagedResponse<MovieResponse>>

    fun searchMovieWithPage(query: String, page: Int): Single<PagedResponse<MovieResponse>>

    fun loadMovieDetail(movieId: Int): Single<MovieDetailResponse>

    fun searchTVShow(query: String): Single<PagedResponse<TVShowResponse>>

    fun discoverMovies(): Single<List<MovieResponse>>

    fun topRatedMovies(): Single<List<MovieResponse>>

    fun nowShowingMovies(): Single<List<MovieResponse>>

    fun upcomingMovies(): Single<List<MovieResponse>>
}