package com.hiden.challenge.data.source.repository

import com.hiden.challenge.data.entity.MovieDetailResponse
import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.data.entity.PagedResponse
import com.hiden.challenge.data.entity.TVShowResponse
import com.hiden.challenge.data.source.remote.RemoteMoviesDataSource
import io.reactivex.Single
import javax.inject.Inject

interface MoviesRepository {

    fun searchMovie(query: String): Single<PagedResponse<MovieResponse>>

    fun searchMovieWithPage(query: String, page: Int): Single<PagedResponse<MovieResponse>>

    fun loadMovieDetail(movieId: Int): Single<MovieDetailResponse>

    fun searchTVShow(query: String): Single<PagedResponse<TVShowResponse>>

    fun discoverMovies(): Single<PagedResponse<MovieResponse>>

    fun topRatedMovies(): Single<PagedResponse<MovieResponse>>

    fun nowShowingMovies(): Single<PagedResponse<MovieResponse>>

    fun upcomingMovies(): Single<PagedResponse<MovieResponse>>
}