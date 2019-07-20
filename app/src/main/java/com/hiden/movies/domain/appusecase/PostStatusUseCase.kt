package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.PostStatusResponse
import com.hiden.movies.data.source.remote.RemoteMoviesDataSource
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class PostStatusUseCase @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
): NewSingleUseCase<String,PostStatusResponse>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseSingle(params: String?): Single<PostStatusResponse> {
        return requireNotNull(params).run(remoteMoviesDataSource::postStatus)
    }

}