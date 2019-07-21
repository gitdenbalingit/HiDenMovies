package com.hiden.movies.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.domain.appusecase.*
import com.hiden.movies.presentation.common.arch.BaseViewModel
import com.hiden.movies.presentation.model.MovieDataView
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val loadNowShowingMoviesUseCase: LoadNowShowingMoviesUseCase,
    private val loadDiscoverMoviesUseCase: LoadDiscoverMoviesUseCase,
    private val loadTopRatedMoviesUseCase: LoadTopRatedMoviesUseCase,
    private val loadUpcomingMoviesUseCase: LoadUpcomingMoviesUseCase,


    private val getGamesUseCase: GetGamesUseCase,
    private val getStagesUseCase: GetStagesUseCase,
    private val getPlayersUseCase: GetPlayersUseCase
) : BaseViewModel() {


    val converItem: LiveData<Result<MovieDataView>> get() = coverItemResultLiveData
    private val coverItemResultLiveData = MutableLiveData<Result<MovieDataView>>()

    val topRatedList: LiveData<Result<List<MovieDataView>>> get() = topRatedListResultLiveData
    private val topRatedListResultLiveData = MutableLiveData<Result<List<MovieDataView>>>()

    val discoverList: LiveData<Result<List<MovieDataView>>> get() = discoverListResultLiveData
    private val discoverListResultLiveData = MutableLiveData<Result<List<MovieDataView>>>()

    val upcomingList: LiveData<Result<List<MovieDataView>>> get() = upcomingListResultLiveData
    private val upcomingListResultLiveData = MutableLiveData<Result<List<MovieDataView>>>()



    fun getGames(){
        addToDisposables(getGamesUseCase
                .getObservable("")
                .subscribe(
                        { Log.v("PIA","Success - "+it[0].name)},
                        {Log.v("PIA","Error - "+it.localizedMessage)}
                ))
    }

    fun getStages(){
        addToDisposables(getStagesUseCase
                .getObservable("")
                .subscribe(
                        { Log.v("PIA","getStagesUseCase Success - "+it[0].name)},
                        {Log.v("PIA"," getStagesUseCase Error - "+it.localizedMessage)}
                ))
    }


    fun getPlayers(){
        addToDisposables(getPlayersUseCase
                .getObservable("")
                .subscribe(
                        { Log.v("PIA","getPlayers Success - "+it[0].profile.username)},
                        {Log.v("PIA"," getPlayers Error - "+it.localizedMessage)}
                ))
    }



    fun loadNowShowingMovie(){
        addToDisposables(loadNowShowingMoviesUseCase
            .getObservable(null)
            .map { it[7] }
            .subscribe(
                {
                    coverItemResultLiveData.postValue(Result.success(it))
                },
                {
                    coverItemResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ))
    }

    fun loadTopRatedMovies(){
        addToDisposables(loadTopRatedMoviesUseCase
            .getObservable(null)
            .subscribe(
                {
                    topRatedListResultLiveData.postValue(Result.success(it))
                },
                {
                    topRatedListResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ))
    }

    fun loadUpcomingMovies(){
        addToDisposables(loadUpcomingMoviesUseCase
            .getObservable(null)
            .subscribe(
                {
                    upcomingListResultLiveData.postValue(Result.success(it))
                },
                {
                    upcomingListResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ))
    }


    fun loadDiscoverMovies(){
        addToDisposables(loadDiscoverMoviesUseCase
            .getObservable(null)
            .subscribe(
                {
                    discoverListResultLiveData.postValue(Result.success(it))
                },
                {
                    discoverListResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ))
    }


}