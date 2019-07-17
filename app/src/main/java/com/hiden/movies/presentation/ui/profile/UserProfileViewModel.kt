package com.hiden.movies.presentation.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.movies.domain.appusecase.GetUserStatusesUseCase
import com.hiden.movies.domain.appusecase.GetUserUseCase
import com.hiden.movies.domain.appusecase.PostStatusUseCase
import com.hiden.movies.domain.appusecase.SearchTweetUseCase
import com.hiden.movies.presentation.common.arch.BaseViewModel
import com.hiden.movies.presentation.model.MovieDataView
import com.hiden.movies.presentation.model.UserDataView
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
        private val getUserUseCase: GetUserUseCase,
        private val getUserStatusesUseCase: GetUserStatusesUseCase
//        private val searchTweetUseCase: SearchTweetUseCase,
//        private val postStatusUseCase: PostStatusUseCase
) : BaseViewModel() {



    val userData : LiveData<Result<UserDataView>> get() = userResultLiveData
    private val userResultLiveData = MutableLiveData<Result<UserDataView>>()



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
                        { Log.v("PIA","Sucess status - "+it[0].text)},
                        { Log.v("PIA","Error  statuts- "+it.localizedMessage)}
                ))
    }


}