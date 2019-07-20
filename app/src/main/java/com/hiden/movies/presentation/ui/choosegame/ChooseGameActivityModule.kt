package com.hiden.movies.presentation.ui.choosegame

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.data.entity.GameResultResponse
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.GamesItemViewHolder
import com.hiden.movies.presentation.common.viewholder.MovieItemViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
import com.hiden.movies.presentation.model.MovieDataView
import com.hiden.movies.presentation.ui.detail.DetailActivity
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class ChooseGameActivityModule {

    @Provides
    fun provideGamesItemViewHolderFactory(activity: ChooseGameActivity): ViewHolderFactory<BindingViewHolder<GameResultResponse>> =
            GamesItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: ChooseGameActivity): AppCompatActivity = activity
}