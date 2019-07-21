package com.hiden.challenge.domain.baseusecase

import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class NewSingleUseCase<in Params, T> (
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    protected abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    fun getObservable(params: Params?): Single<T> =
        buildUseCaseSingle(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
}