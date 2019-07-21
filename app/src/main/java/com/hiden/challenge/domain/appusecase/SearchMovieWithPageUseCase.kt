package com.hiden.challenge.domain.appusecase

import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.data.source.repository.MoviesRepository
import com.hiden.challenge.domain.baseusecase.NewSingleUseCase
import com.hiden.challenge.domain.params.SearchMovieParam
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject

class SearchMovieWithPageUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : NewSingleUseCase<SearchMovieParam, List<MovieResponse>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: SearchMovieParam?): Single<List<MovieResponse>> =
    moviesRepository.searchMovieWithPage(params!!.query,params.page).map { it.results }

}