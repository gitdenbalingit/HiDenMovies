package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.Mapper.toDataView
import com.hiden.movies.data.entity.MovieDetailResponse
import com.hiden.movies.data.source.repository.MoviesRepository
import com.hiden.movies.domain.baseusecase.NewSingleUseCase
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
import com.hiden.movies.presentation.model.MovieDetailView
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