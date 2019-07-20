package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.Mapper.toDataView
import com.hiden.movies.data.entity.StatusesResponse
import com.hiden.movies.data.source.remote.RemoteMoviesDataSource
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
import com.hiden.movies.presentation.model.UserDataView
import com.hiden.movies.presentation.model.UserStatusDataView
import io.reactivex.Single
import javax.inject.Inject

class GetUserStatusesUseCase @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread)
    : NewSingleUseCase<Any?, List<UserStatusDataView>>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: Any?): Single<List<UserStatusDataView>> {
        return remoteMoviesDataSource.getStatuses().map { it.map { it.toDataView() } }
    }

}