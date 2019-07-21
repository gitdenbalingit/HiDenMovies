package com.hiden.challenge.presentation.ui.chooseplayers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.challenge.data.entity.PlayerResponse
import com.hiden.challenge.domain.appusecase.GetPlayersUseCase
import com.hiden.challenge.presentation.common.arch.BaseViewModel
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