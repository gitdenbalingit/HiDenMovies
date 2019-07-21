package com.hiden.movies.presentation.ui.chooseplayers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.movies.data.entity.PlayerResponse
import com.hiden.movies.domain.appusecase.GetPlayersUseCase
import com.hiden.movies.presentation.common.arch.BaseViewModel
import javax.inject.Inject

class ChoosePlayersViewModel @Inject constructor(
        private val getPlayersUseCase: GetPlayersUseCase
): BaseViewModel() {


    val playersList: LiveData<Result<List<PlayerResponse>>> get() = playersListResultLiveData
    private val playersListResultLiveData =  MutableLiveData<Result<List<PlayerResponse>>>()

    fun getPlayers(){
        addToDisposables(getPlayersUseCase
                .getObservable("")
                .subscribe(
                        { playersListResultLiveData.postValue(Result.success(it))},
                        { playersListResultLiveData.postValue(Result.failure(Throwable("Error get players")))}
                ))
    }
}