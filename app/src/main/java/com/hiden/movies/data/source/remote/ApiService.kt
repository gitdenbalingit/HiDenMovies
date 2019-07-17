package com.hiden.movies.data.source.remote

import com.hiden.movies.data.entity.*
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

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


    //Wizeline

    @GET("api/user")
    fun getUser() : Single<UserResponse>

    @GET("api/statuses/user_timeline")
    fun getUserStatuses() : Single<List<StatusesResponse>>

    @GET("api/search/{query}")
    fun searchTweet(@Path("query") query: String) : Single<SearchResponse>

    @FormUrlEncoded
    @POST("api/statuses/update")
    fun postStatus(@Field("status") status: String) : Single<PostStatusResponse>


}