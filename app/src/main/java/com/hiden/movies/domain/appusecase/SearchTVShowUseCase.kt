package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.TVShowResponse
import com.hiden.movies.data.source.repository.MoviesRepository
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
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