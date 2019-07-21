package com.hiden.challenge.domain.appusecase

import com.hiden.challenge.data.entity.Mapper.toDataView
import com.hiden.challenge.data.entity.MovieDetailResponse
import com.hiden.challenge.data.source.repository.MoviesRepository
import com.hiden.challenge.domain.baseusecase.NewSingleUseCase
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import com.hiden.challenge.presentation.model.MovieDetailView
import io.reactivex.Single
import javax.inject.Inject

class LoadMovieDetailUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : NewSingleUseCase<Int,MovieDetailView>(threadExecutor,postExecutionThread) {

    override fun buildUseCaseSingle(params: Int?): Single<MovieDetailView> =
         requireNotNull(params).run(moviesRepository::loadMovieDetail).map { it.toDataView() }
}