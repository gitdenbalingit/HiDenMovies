package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.StagesResponse
import com.hiden.movies.data.source.remote.RemoteMoviesDataSource
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
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