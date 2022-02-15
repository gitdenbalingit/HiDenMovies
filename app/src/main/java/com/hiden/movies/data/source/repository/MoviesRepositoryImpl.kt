package com.hiden.movies.data.source.repository

import com.hiden.movies.data.entity.MovieDetailResponse
import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.data.entity.PagedResponse
import com.hiden.movies.data.entity.TVShowResponse
import com.hiden.movies.data.source.remote.RemoteMoviesDataSource
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource
): MoviesRepository {

   override fun searchMovie(query: String): Single<PagedResponse<MovieResponse>> =
            query.run(remoteMoviesDataSource::searchMovie)

    override fun searchMovieWithPage(query: String, page: Int): Single<PagedResponse<MovieResponse>> =
            remoteMoviesDataSource.searchMovieWithPage(query, page)

    override fun loadMovieDetail(movieId: Int): Single<MovieDetailResponse> =
            movieId.run(remoteMoviesDataSource::loadMovieDetail)

    override fun searchTVShow(query: String): Single<PagedResponse<TVShowResponse>> =
            query.run(remoteMoviesDataSource::searchTVShow)

    override fun discoverMovies(): Single<List<MovieResponse>> =
            remoteMoviesDataSource
                .discoverMovies()
                .map { it.results }

    override fun topRatedMovies(): Single<List<MovieResponse>> =
            remoteMoviesDataSource
                .topRatedMovies()
                .map { it.results }

    override fun nowShowingMovies(): Single<List<MovieResponse>> =
            remoteMoviesDataSource
                .noShowingMovies()
                .map { it.results }

    override fun upcomingMovies(): Single<List<MovieResponse>> =
            remoteMoviesDataSource
                .upcomingMovies()
                .map { it.results }
}