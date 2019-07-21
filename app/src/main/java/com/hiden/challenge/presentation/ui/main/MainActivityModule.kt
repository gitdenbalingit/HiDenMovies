package com.hiden.challenge.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.hiden.challenge.presentation.common.viewholder.BindingViewHolder
import com.hiden.challenge.presentation.common.viewholder.ViewHolderFactory
import com.hiden.challenge.presentation.common.viewholder.MovieItemViewHolder
import com.hiden.challenge.presentation.di.PerActivity
import com.hiden.challenge.presentation.di.modules.BaseActivityModule
import com.hiden.challenge.presentation.model.MovieDataView
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class MainActivityModule {

    @Provides
    fun provideMovieItemHolderFactory(activity: MainActivity): ViewHolderFactory<BindingViewHolder<MovieDataView>> =
        MovieItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: MainActivity): AppCompatActivity = activity
}