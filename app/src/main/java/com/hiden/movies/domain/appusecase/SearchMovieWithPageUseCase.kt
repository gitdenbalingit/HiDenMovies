package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.data.source.repository.MoviesRepository
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.domain.params.SearchMovieParam
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
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