package com.hiden.movies.presentation.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.movies.data.entity.Mapper.toDataView
import com.hiden.movies.domain.appusecase.GetUserStatusesUseCase
import com.hiden.movies.domain.appusecase.GetUserUseCase
import com.hiden.movies.domain.appusecase.PostStatusUseCase
import com.hiden.movies.domain.appusecase.SearchTweetUseCase
import com.hiden.movies.presentation.common.arch.BaseViewModel
import com.hiden.movies.presentation.model.MovieDataView
import com.hiden.movies.presentation.model.UserDataView
import com.hiden.movies.presentation.model.UserStatusDataView
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
        private val getUserUseCase: GetUserUseCase,
        private val getUserStatusesUseCase: GetUserStatusesUseCase
) : BaseViewModel() {



    val userData : LiveData<Result<UserDataView>> get() = userResultLiveData
    private val userResultLiveData = MutableLiveData<Result<UserDataView>>()

    val statusesData: LiveData<Result<List<UserStatusDataView>>> get() = statusesResultLiveData
    private val statusesResultLiveData = MutableLiveData<Result<List<UserStatusDataView>>>()



    fun loadUser(){
        addToDisposables(getUserUseCase
                .getObservable(null)
                .subscribe(
                        { userResultLiveData.postValue(Result.success(it))},
                        { userResultLiveData.postValue(Result.failure(Throwable("Error loading user")))}
                ))
    }

    fun loadUserStatuses(){
        addToDisposables(getUserStatusesUseCase
                .getObservable(null)
                .subscribe(
                        { statusesResultLiveData.postValue(Result.success(it))},
                        { statusesResultLiveData.postValue(Result.failure(Throwable("Error loading statuses")))}
                ))
    }


}