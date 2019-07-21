package com.hiden.challenge.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.data.source.remote.SearchMovieLoader
import com.hiden.challenge.domain.appusecase.SearchMovieWithPageUseCase
import com.hiden.challenge.presentation.common.arch.BaseViewModel
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(
    private val searchMovieWithPageUseCase: SearchMovieWithPageUseCase
) : BaseViewModel() {



    var listing: LiveData<PagedList<MovieResponse>> = object : LiveData<PagedList<MovieResponse>>() {}
    private lateinit var sourceFactory: SearchMovieLoader.Factory


    fun searchMovieWithPage(query: String): LiveData<PagedList<MovieResponse>>  {
        sourceFactory = SearchMovieLoader.Factory(
            searchMovieWithPageUseCase,
            query
        )

        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20 * 2)
            .setEnablePlaceholders(false)
            .build()

        listing = LivePagedListBuilder<Int, MovieResponse>(sourceFactory!!, config).build()

        listing.value?.dataSource?.invalidate()

        return listing

    }

    fun retry() {
        sourceFactory!!.sourceLiveData.value?.retry()
    }



}