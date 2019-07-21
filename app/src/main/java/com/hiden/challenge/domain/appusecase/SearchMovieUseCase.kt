package com.hiden.challenge.domain.appusecase

import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.data.source.repository.MoviesRepository
import com.hiden.challenge.domain.baseusecase.NewSingleUseCase
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : NewSingleUseCase<String, List<MovieResponse>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: String?): Single<List<MovieResponse>> =
        requireNotNull(params).run(moviesRepository::searchMovie).map { it.results }

}