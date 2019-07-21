package com.hiden.challenge.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.domain.appusecase.SearchMovieWithPageUseCase
import com.hiden.challenge.domain.params.SearchMovieParam
import com.hiden.challenge.presentation.model.State
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class SearchMovieLoader(
    private val searchMovieWithPageUseCase: SearchMovieWithPageUseCase,
    private val query: String
) : PageKeyedDataSource<Int,MovieResponse>() {

    val state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieResponse>) {
        searchMovieWithPageUseCase
           .getObservable(SearchMovieParam(query,1))
           .subscribe(
               {
                   updateState(State.DONE)
                   callback.onResult(it, null, 2)
               },
               {
                   updateState(State.ERROR)
                   setRetry(Action { loadInitial(params, callback) })
               }
           )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieResponse>) {
        updateState(State.LOADING)
        searchMovieWithPageUseCase
            .getObservable(SearchMovieParam(query,params.key))
            .subscribe(
                {
                    updateState(State.DONE)
                    callback.onResult(it,params.key + 1)
                },
                {
                    updateState(State.ERROR)
                    setRetry(Action { loadAfter(params, callback) })
                }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieResponse>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }


    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

    class Factory(
        private val searchMovieWithPageUseCase: SearchMovieWithPageUseCase,
        private val query: String
    ) : DataSource.Factory<Int, MovieResponse>() {

        val sourceLiveData = MutableLiveData<SearchMovieLoader>()


        override fun create(): DataSource<Int, MovieResponse> {

            val dataSource = SearchMovieLoader(
                searchMovieWithPageUseCase,
                query
            )

            sourceLiveData.postValue(dataSource)
            return dataSource
        }

    }

}