package com.hiden.movies.domain.baseusecase

import com.hiden.movies.presentation.common.arch.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

abstract class NewCompletableUseCase<Params>(private val threadExecutor: ThreadExecutor) {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Completable

    fun getObservable(params: Params? = null): Completable =
        buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))

}