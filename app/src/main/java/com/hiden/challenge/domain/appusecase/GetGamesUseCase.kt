package com.hiden.challenge.domain.appusecase

import com.hiden.challenge.data.entity.GameResultResponse
import com.hiden.challenge.data.source.remote.RemoteMoviesDataSource
import com.hiden.challenge.domain.baseusecase.NewSingleUseCase
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
): NewSingleUseCase<Any?,List<GameResultResponse>>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseSingle(params: Any?): Single<List<GameResultResponse>> {
        return remoteMoviesDataSource.getGames().map { it.results }
    }
}