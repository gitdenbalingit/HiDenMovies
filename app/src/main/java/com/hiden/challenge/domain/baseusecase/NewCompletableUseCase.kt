package com.hiden.challenge.domain.baseusecase

import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

abstract class NewCompletableUseCase<Params>(private val threadExecutor: ThreadExecutor) {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Completable

    fun getObservable(params: Params? = null): Completable =
        buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))

}