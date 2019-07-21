package com.hiden.challenge.data.source.remote

import com.hiden.challenge.data.entity.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/games/?limit=0&offset=0")
    fun getGames(): Single<PageResponse<GameResultResponse>>


    @GET("api/stages/?limit=0&offset=0")
    fun getStages(): Single<PageResponse<StagesResponse>>

    @GET("api/players/?limit=0&offset=0")
    fun getPlayers(): Single<PageResponse<PlayerResponse>>


}