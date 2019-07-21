package com.hiden.challenge.presentation.ui.search

import androidx.appcompat.app.AppCompatActivity
import com.hiden.challenge.presentation.common.viewholder.BindingViewHolder
import com.hiden.challenge.presentation.common.viewholder.ViewHolderFactory
import com.hiden.challenge.data.entity.MovieResponse
import com.hiden.challenge.presentation.common.viewholder.SearchItemMovieViewHolder
import com.hiden.challenge.presentation.di.PerActivity
import com.hiden.challenge.presentation.di.modules.BaseActivityModule
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