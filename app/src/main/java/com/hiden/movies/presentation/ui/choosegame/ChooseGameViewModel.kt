package com.hiden.movies.presentation.ui.choosegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.movies.data.entity.GameResultResponse
import com.hiden.movies.domain.appusecase.GetGamesUseCase
import com.hiden.movies.presentation.common.arch.BaseViewModel
import javax.inject.Inject

class ChooseGameViewModel @Inject constructor(
        private val getGamesUseCase: GetGamesUseCase
) : BaseViewModel() {


    val gamesList: LiveData<Result<List<GameResultResponse>>> get() = gamesListResultLiveData
    private val gamesListResultLiveData = MutableLiveData<Result<List<GameResultResponse>>>()


    fun getGames(){
        addToDisposables(getGamesUseCase
                .getObservable("")
                .subscribe(
                        { gamesListResultLiveData.postValue(Result.success(it))},
                        { gamesListResultLiveData.postValue(Result.failure(Throwable("Error fetch games")))}
                ))
    }
}