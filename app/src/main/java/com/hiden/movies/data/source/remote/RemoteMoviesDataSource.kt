package com.hiden.movies.data.source.remote

import javax.inject.Inject

class RemoteMoviesDataSource @Inject constructor(
    private val apiService: ApiService
) {

    fun searchMovie(query: String) =
        apiService.searchMovie(query, "c12cad84bcfc673c7863e4de940ab4ee")

    fun searchMovieWithPage(query: String, page: Int) =
            apiService.searchMovieWithPage(query, "c12cad84bcfc673c7863e4de940ab4ee", page)

    fun loadMovieDetail(movieId: Int) =
            apiService.loadMovieDetails(movieId, "c12cad84bcfc673c7863e4de940ab4ee")

    fun searchTVShow(query: String) =
            apiService.searchTVShow(query, "c12cad84bcfc673c7863e4de940ab4ee")

    fun discoverMovies() =
            apiService.discoverMovies("c12cad84bcfc673c7863e4de940ab4ee")

    fun topRatedMovies() =
            apiService.topRatedMovies("c12cad84bcfc673c7863e4de940ab4ee")

    fun noShowingMovies() =
        apiService.nowShowingMovies("c12cad84bcfc673c7863e4de940ab4ee")

    fun upcomingMovies() =
        apiService.upcomingMovies("c12cad84bcfc673c7863e4de940ab4ee")




    fun getUser() = apiService.getUser()

    fun getStatuses() = apiService.getUserStatuses()

    fun searchStatus(query: String) = apiService.searchTweet(query)

    fun postStatus(status: String) = apiService.postStatus(status)
}