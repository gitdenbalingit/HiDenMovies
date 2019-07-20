package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.GameResultResponse
import com.hiden.movies.data.source.remote.RemoteMoviesDataSource
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
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