package com.hiden.challenge.presentation.ui.choosestage

import androidx.appcompat.app.AppCompatActivity
import com.hiden.challenge.data.entity.GameResultResponse
import com.hiden.challenge.data.entity.StagesResponse
import com.hiden.challenge.presentation.common.viewholder.BindingViewHolder
import com.hiden.challenge.presentation.common.viewholder.GamesItemViewHolder
import com.hiden.challenge.presentation.common.viewholder.StagesItemViewHolder
import com.hiden.challenge.presentation.common.viewholder.ViewHolderFactory
import com.hiden.challenge.presentation.di.PerActivity
import com.hiden.challenge.presentation.di.modules.BaseActivityModule
import com.hiden.challenge.presentation.ui.choosegame.ChooseGameActivity
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