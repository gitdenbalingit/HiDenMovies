package com.hiden.movies.presentation.ui.choosestage

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.data.entity.GameResultResponse
import com.hiden.movies.data.entity.StagesResponse
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.GamesItemViewHolder
import com.hiden.movies.presentation.common.viewholder.StagesItemViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
import com.hiden.movies.presentation.ui.choosegame.ChooseGameActivity
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class ChooseStageActivityModule {

    @Provides
    fun provideStagesItemViewHolderFactory(activity: ChooseStageActivity): ViewHolderFactory<BindingViewHolder<StagesResponse>> =
            StagesItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: ChooseStageActivity): AppCompatActivity = activity
}