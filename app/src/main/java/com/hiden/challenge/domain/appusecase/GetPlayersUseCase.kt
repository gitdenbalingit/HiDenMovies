package com.hiden.challenge.domain.appusecase

import com.hiden.challenge.data.entity.PlayerResponse
import com.hiden.challenge.data.entity.StagesResponse
import com.hiden.challenge.data.source.remote.RemoteMoviesDataSource
import com.hiden.challenge.domain.baseusecase.NewSingleUseCase
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
): NewSingleUseCase<Any?, List<PlayerResponse>>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseSingle(params: Any?): Single<List<PlayerResponse>> {
        return remoteMoviesDataSource.getPlayers().map { it.results }
    }
}