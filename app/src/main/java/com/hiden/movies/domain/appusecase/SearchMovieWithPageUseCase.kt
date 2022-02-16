package com.hiden.movies.domain.appusecase

import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.data.source.repository.MoviesRepository
import com.hiden.movies.domain.params.SearchMovieParam
import com.hiden.movies.presentation.common.arch.ThreadExecutor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchMovieWithPageUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val threadExecutor: ThreadExecutor
) {

    operator fun invoke(param: SearchMovieParam) : Single<List<MovieResponse>> {
        return requireNotNull(param).run {
            moviesRepository
                .searchMovieWithPage(query,page)
                .subscribeOn(Schedulers.from(threadExecutor))
                .doOnError { it.printStackTrace() }

        }


    }
}