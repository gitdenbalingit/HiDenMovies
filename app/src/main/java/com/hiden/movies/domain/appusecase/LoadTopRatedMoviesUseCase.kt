package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.Mapper.toDataView
import com.hiden.movies.data.source.repository.MoviesRepository
import com.hiden.movies.presentation.common.arch.ThreadExecutor
import com.hiden.movies.presentation.model.MovieDataView
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoadTopRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val threadExecutor: ThreadExecutor
) {
    operator fun invoke(): Single<List<MovieDataView>> {
        return moviesRepository
            .topRatedMovies()
            .map { it.map { item -> item.toDataView() } }
            .subscribeOn(Schedulers.from(threadExecutor))
            .doOnError { it.printStackTrace() }

    }
}