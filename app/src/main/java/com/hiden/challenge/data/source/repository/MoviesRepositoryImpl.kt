package com.hiden.challenge.data.source.repository

import com.hiden.challenge.data.entity.MovieDetailResponse
import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.data.entity.PagedResponse
import com.hiden.challenge.data.entity.TVShowResponse
import com.hiden.challenge.data.source.remote.RemoteMoviesDataSource
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource
): MoviesRepository {

   override fun searchMovie(query: String): Single<PagedResponse<MovieResponse>> =
            requireNotNull(query).run(remoteMoviesDataSource::searchMovie)

    override fun searchMovieWithPage(query: String, page: Int): Single<PagedResponse<MovieResponse>> =
            remoteMoviesDataSource.searchMovieWithPage(query, page)

    override fun loadMovieDetail(movieId: Int): Single<MovieDetailResponse> =
            requireNotNull(movieId).run(remoteMoviesDataSource::loadMovieDetail)

    override fun searchTVShow(query: String): Single<PagedResponse<TVShowResponse>> =
            requireNotNull(query).run(remoteMoviesDataSource::searchTVShow)

    override fun discoverMovies(): Single<PagedResponse<MovieResponse>> =
            remoteMoviesDataSource.discoverMovies()

    override fun topRatedMovies(): Single<PagedResponse<MovieResponse>> =
            remoteMoviesDataSource.topRatedMovies()

    override fun nowShowingMovies(): Single<PagedResponse<MovieResponse>> =
            remoteMoviesDataSource.noShowingMovies()

    override fun upcomingMovies(): Single<PagedResponse<MovieResponse>> =
            remoteMoviesDataSource.upcomingMovies()
}