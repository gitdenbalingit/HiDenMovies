package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.StatusesResponse
import com.hiden.movies.data.source.remote.RemoteMoviesDataSource
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class GetUserStatusesUseCase @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread)
    : NewSingleUseCase<Any?, List<StatusesResponse>>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: Any?): Single<List<StatusesResponse>> {

        return remoteMoviesDataSource.getStatuses()
    }

}