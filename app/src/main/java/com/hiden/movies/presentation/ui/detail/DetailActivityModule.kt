package com.hiden.movies.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.presentation.common.viewholder.MovieItemViewHolder
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
import com.hiden.movies.presentation.model.MovieDataView
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class DetailActivityModule {

    @Provides
    fun providedMovieItemHolderFactory(activity: DetailActivity): ViewHolderFactory<BindingViewHolder<MovieDataView>> =
        MovieItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: DetailActivity): AppCompatActivity = activity
}