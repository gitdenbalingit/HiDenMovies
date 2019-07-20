package com.hiden.movies.presentation.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.movies.data.entity.PostStatusResponse
import com.hiden.movies.domain.appusecase.PostStatusUseCase
import com.hiden.movies.presentation.common.arch.BaseViewModel
import javax.inject.Inject

class PostStatusViewModel @Inject constructor(
        private val postStatusUseCase: PostStatusUseCase
): BaseViewModel() {


    val postResponse : LiveData<Result<PostStatusResponse>> get() = postStatusResultLiveData
    private val postStatusResultLiveData = MutableLiveData<Result<PostStatusResponse>>()




    fun postStatus(status: String){
        addToDisposables(postStatusUseCase
                .getObservable(status)
                .subscribe(
                        { postStatusResultLiveData.postValue(Result.success(it))},
                        { postStatusResultLiveData.postValue(Result.failure(Throwable("Error post")))}
                ))
    }
}