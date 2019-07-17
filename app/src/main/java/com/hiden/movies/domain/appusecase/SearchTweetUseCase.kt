package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.Mapper.toDataView
import com.hiden.movies.data.entity.SearchResponse
import com.hiden.movies.data.source.remote.RemoteMoviesDataSource
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
import com.hiden.movies.presentation.model.UserStatusDataView
import io.reactivex.Single
import javax.inject.Inject

class SearchTweetUseCase @Inject constructor(
        private val remoteMoviesDataSource: RemoteMoviesDataSource,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread)
    :NewSingleUseCase<String,List<UserStatusDataView>>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseSingle(params: String?): Single<List<UserStatusDataView>> {
        return remoteMoviesDataSource.searchStatus(params!!).map { it.statuses.map { it.toDataView() } }
    }


}