package com.hiden.challenge.data.source.remote

import javax.inject.Inject

class RemoteMoviesDataSource @Inject constructor(
    private val apiService: ApiService
) {


    fun getGames() = apiService.getGames()

    fun getStages() = apiService.getStages()

    fun getPlayers() = apiService.getPlayers()
}