package com.hiden.challenge.domain.appusecase

import com.hiden.challenge.data.entity.StagesResponse
import com.hiden.challenge.data.source.remote.RemoteMoviesDataSource
import com.hiden.challenge.domain.baseusecase.NewSingleUseCase
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class GetStagesUseCase @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
): NewSingleUseCase<Any?, List<StagesResponse>>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseSingle(params: Any?): Single<List<StagesResponse>> {
        return remoteMoviesDataSource.getStages().map { it.results }
    }
}