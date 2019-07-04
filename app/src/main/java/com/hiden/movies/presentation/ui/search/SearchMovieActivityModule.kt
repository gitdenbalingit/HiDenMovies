package com.hiden.movies.presentation.ui.search

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.data.entity.MovieResponse
import com.hiden.movies.presentation.common.viewholder.SearchItemMovieViewHolder
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class SearchMovieActivityModule {


    @Provides
    fun provideSearchItemMovieViewHolderFactory(activity: SearchMovieActivity): ViewHolderFactory<BindingViewHolder<MovieResponse>> =
        SearchItemMovieViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: SearchMovieActivity): AppCompatActivity = activity
}