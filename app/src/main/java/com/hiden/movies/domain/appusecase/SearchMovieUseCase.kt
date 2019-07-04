package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.data.source.repository.MoviesRepository
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
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