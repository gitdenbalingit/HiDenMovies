package com.hiden.challenge.domain.appusecase

import com.hiden.challenge.data.entity.Mapper.toDataView
import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.data.source.repository.MoviesRepository
import com.hiden.challenge.domain.baseusecase.NewSingleUseCase
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import com.hiden.challenge.presentation.model.MovieDataView
import io.reactivex.Single
import io.reactivex.rxkotlin.toFlowable
import javax.inject.Inject

class LoadNowShowingMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : NewSingleUseCase<Any?, List<MovieDataView>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: Any?): Single<List<MovieDataView>> =
        moviesRepository.nowShowingMovies().map { it.results }.flatMap {
            it.map { it.toDataView() }.toFlowable().toList()
        }

}