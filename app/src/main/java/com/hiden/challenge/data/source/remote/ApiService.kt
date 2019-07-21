package com.hiden.challenge.data.source.remote

import com.hiden.challenge.data.entity.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /***
     *
     * HMLET
     */
    @GET("search/movie")
    fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): Single<PagedResponse<MovieResponse>>

    @GET("search/movie")
    fun searchMovieWithPage(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<PagedResponse<MovieResponse>>

    @GET("search/tv")
    fun searchTVShow(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): Single<PagedResponse<TVShowResponse>>


    @GET("movie/{id}?append_to_response=similar")
    fun loadMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieDetailResponse>


    @GET("discover/movie")
    fun discoverMovies(
        @Query("api_key") apiKey: String
    ): Single<PagedResponse<MovieResponse>>


    @GET("movie/top_rated")
    fun topRatedMovies(
        @Query("api_key") apiKey: String
    ): Single<PagedResponse<MovieResponse>>

    @GET("movie/now_playing")
    fun nowShowingMovies(
        @Query("api_key") apiKey: String
    ): Single<PagedResponse<MovieResponse>>
    

    @GET("movie/upcoming")
    fun upcomingMovies(
        @Query("api_key") apiKey: String
    ): Single<PagedResponse<MovieResponse>>


    @GET("api/games/?limit=0&offset=0")
    fun getGames(): Single<PageResponse<GameResultResponse>>


    @GET("api/stages/?limit=0&offset=0")
    fun getStages(): Single<PageResponse<StagesResponse>>

    @GET("api/players/?limit=0&offset=0")
    fun getPlayers(): Single<PageResponse<PlayerResponse>>


}