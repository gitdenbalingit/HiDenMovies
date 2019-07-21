package com.hiden.movies.presentation.ui.chooseplayers

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.data.entity.PlayerResponse
import com.hiden.movies.presentation.common.viewholder.PlayersItemViewHolder
import com.hiden.movies.presentation.common.viewholder.SelectionBindingViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
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