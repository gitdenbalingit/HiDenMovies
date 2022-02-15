package com.hiden.movies.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.movies.domain.appusecase.LoadDiscoverMoviesUseCase
import com.hiden.movies.domain.appusecase.LoadNowShowingMoviesUseCase
import com.hiden.movies.domain.appusecase.LoadTopRatedMoviesUseCase
import com.hiden.movies.domain.appusecase.LoadUpcomingMoviesUseCase
import com.hiden.movies.presentation.common.arch.BaseViewModel
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.model.MovieDataView
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import kotlin.random.Random

class MainActivityViewModel @Inject constructor(
    private val postExecutionThread: PostExecutionThread,
    private val loadNowShowingMoviesUseCase: LoadNowShowingMoviesUseCase,
    private val loadDiscoverMoviesUseCase: LoadDiscoverMoviesUseCase,
    private val loadTopRatedMoviesUseCase: LoadTopRatedMoviesUseCase,
    private val loadUpcomingMoviesUseCase: LoadUpcomingMoviesUseCase
) : BaseViewModel() {


    val converItem: LiveData<Result<MovieDataView>> get() = coverItemResultLiveData
    private val coverItemResultLiveData = MutableLiveData<Result<MovieDataView>>()

    val topRatedList: LiveData<Result<List<MovieDataView>>> get() = topRatedListResultLiveData
    private val topRatedListResultLiveData = MutableLiveData<Result<List<MovieDataView>>>()

    val discoverList: LiveData<Result<List<MovieDataView>>> get() = discoverListResultLiveData
    private val discoverListResultLiveData = MutableLiveData<Result<List<MovieDataView>>>()

    val upcomingList: LiveData<Result<List<MovieDataView>>> get() = upcomingListResultLiveData
    private val upcomingListResultLiveData = MutableLiveData<Result<List<MovieDataView>>>()



    fun loadNowShowingMovie(){
        loadNowShowingMoviesUseCase.invoke()
            .observeOn(postExecutionThread.scheduler)
            .map { it[Random.nextInt(it.size)] }
            .subscribeBy(
                onSuccess = {coverItemResultLiveData.postValue(Result.success(it))},
                onError =  {coverItemResultLiveData.postValue(Result.failure(it))}
            )
            .addTo(compositeDisposable)

    }

    fun loadTopRatedMovies(){
        loadTopRatedMoviesUseCase.invoke()
            .observeOn(postExecutionThread.scheduler)
            .subscribeBy(
                onSuccess = {topRatedListResultLiveData.postValue(Result.success(it))},
                onError =  {topRatedListResultLiveData.postValue(Result.failure(it))}
            )
            .addTo(compositeDisposable)
    }

    fun loadUpcomingMovies(){
        loadUpcomingMoviesUseCase.invoke()
            .observeOn(postExecutionThread.scheduler)
            .subscribeBy(
                onSuccess = {upcomingListResultLiveData.postValue(Result.success(it))},
                onError =  {upcomingListResultLiveData.postValue(Result.failure(it))}
            )
            .addTo(compositeDisposable)
    }


    fun loadDiscoverMovies(){
        loadDiscoverMoviesUseCase.invoke()
            .observeOn(postExecutionThread.scheduler)
            .subscribeBy(
                onSuccess = {discoverListResultLiveData.postValue(Result.success(it))},
                onError =  {discoverListResultLiveData.postValue(Result.failure(it))}
            )
            .addTo(compositeDisposable)

    }


}