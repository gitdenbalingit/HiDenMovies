package com.hiden.challenge.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.challenge.domain.appusecase.LoadMovieDetailUseCase
import com.hiden.challenge.presentation.common.arch.BaseViewModel
import com.hiden.challenge.presentation.model.MovieDetailView
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(
    private val loadMovieDetailUseCase: LoadMovieDetailUseCase
): BaseViewModel() {


    val movieData: LiveData<Result<MovieDetailView>> get() = movieResultLiveData
    private val movieResultLiveData = MutableLiveData<Result<MovieDetailView>>()



    fun loadMovieDetail(id: Int){
        loadMovieDetailUseCase
            .getObservable(id)
            .subscribe(
                {
                    movieResultLiveData.postValue(Result.success(it))
                },
                {
                    movieResultLiveData.postValue(Result.failure(Throwable("error")))
                }
            )
    }
}