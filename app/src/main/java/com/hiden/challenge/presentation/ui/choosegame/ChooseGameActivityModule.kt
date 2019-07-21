package com.hiden.challenge.presentation.ui.choosegame

import androidx.appcompat.app.AppCompatActivity
import com.hiden.challenge.data.entity.GameResultResponse
import com.hiden.challenge.presentation.common.viewholder.BindingViewHolder
import com.hiden.challenge.presentation.common.viewholder.GamesItemViewHolder
import com.hiden.challenge.presentation.common.viewholder.MovieItemViewHolder
import com.hiden.challenge.presentation.common.viewholder.ViewHolderFactory
import com.hiden.challenge.presentation.di.PerActivity
import com.hiden.challenge.presentation.di.modules.BaseActivityModule
import com.hiden.challenge.presentation.model.MovieDataView
import com.hiden.challenge.presentation.ui.detail.DetailActivity
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