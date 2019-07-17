package com.hiden.movies.presentation.ui.searchtweet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hiden.movies.domain.appusecase.SearchTweetUseCase
import com.hiden.movies.presentation.common.arch.BaseViewModel
import com.hiden.movies.presentation.model.UserStatusDataView
import javax.inject.Inject

class SearchTweetViewModel @Inject constructor(
        private val searchTweetUseCase: SearchTweetUseCase
): BaseViewModel() {



    val searchStatusesData: LiveData<Result<List<UserStatusDataView>>> get() = searchResultLiveData
    private val searchResultLiveData = MutableLiveData<Result<List<UserStatusDataView>>>()




    fun searchTweet(query: String){
        Log.v("PIA","searchTweet = "+query)
        addToDisposables(searchTweetUseCase
                .getObservable(query)
                .subscribe(
                        { searchResultLiveData.postValue(Result.success(it))},
                        { searchResultLiveData.postValue(Result.failure(Throwable("Error search")))}
                ))
    }

}