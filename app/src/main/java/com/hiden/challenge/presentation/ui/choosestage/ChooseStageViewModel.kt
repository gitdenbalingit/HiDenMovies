package com.hiden.challenge.presentation.ui.choosestage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.challenge.data.entity.StagesResponse
import com.hiden.challenge.domain.appusecase.GetStagesUseCase
import com.hiden.challenge.presentation.common.arch.BaseViewModel
import javax.inject.Inject

class ChooseStageViewModel @Inject constructor(
        private val getStagesUseCase: GetStagesUseCase
) : BaseViewModel() {



    val stagesList: LiveData<Result<List<StagesResponse>>> get() = stagesListResultLiveData
    private val stagesListResultLiveData = MutableLiveData<Result<List<StagesResponse>>>()


    fun getStages(){
        addToDisposables(getStagesUseCase
                .getObservable("")
                .subscribe(
                        { stagesListResultLiveData.postValue(Result.success(it))},
                        { stagesListResultLiveData.postValue(Result.failure(Throwable("Error")))}
                ))
    }
}