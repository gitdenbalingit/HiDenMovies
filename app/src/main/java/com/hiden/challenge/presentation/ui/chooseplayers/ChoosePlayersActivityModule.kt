package com.hiden.challenge.presentation.ui.chooseplayers

import androidx.appcompat.app.AppCompatActivity
import com.hiden.challenge.data.entity.PlayerResponse
import com.hiden.challenge.presentation.common.viewholder.PlayersItemViewHolder
import com.hiden.challenge.presentation.common.viewholder.SelectionBindingViewHolder
import com.hiden.challenge.presentation.common.viewholder.ViewHolderFactory
import com.hiden.challenge.presentation.di.PerActivity
import com.hiden.challenge.presentation.di.modules.BaseActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class ChoosePlayersActivityModule {

    @Provides
    fun providePlayersItemViewHolderFactory(): ViewHolderFactory<SelectionBindingViewHolder<PlayerResponse>> =
            PlayersItemViewHolder.Factory()

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: ChoosePlayersActivity): AppCompatActivity = activity
}