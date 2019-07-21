package com.hiden.challenge.presentation.ui.choosegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.challenge.data.entity.GameResultResponse
import com.hiden.challenge.domain.appusecase.GetGamesUseCase
import com.hiden.challenge.presentation.common.arch.BaseViewModel
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