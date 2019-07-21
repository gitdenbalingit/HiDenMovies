package com.hiden.challenge.domain.appusecase

import com.hiden.challenge.data.entity.TVShowResponse
import com.hiden.challenge.data.source.repository.MoviesRepository
import com.hiden.challenge.domain.baseusecase.NewSingleUseCase
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class SearchTVShowUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : NewSingleUseCase<String, List<TVShowResponse>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: String?): Single<List<TVShowResponse>> =
        requireNotNull(params).run(moviesRepository::searchTVShow).map { it.results }

}