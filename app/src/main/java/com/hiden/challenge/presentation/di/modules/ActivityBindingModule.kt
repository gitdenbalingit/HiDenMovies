package com.hiden.challenge.presentation.di.modules

import com.hiden.challenge.presentation.di.PerActivity
import com.hiden.challenge.presentation.ui.choosegame.ChooseGameActivity
import com.hiden.challenge.presentation.ui.choosegame.ChooseGameActivityModule
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersActivity
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersActivityModule
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageActivity
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {


    @PerActivity
    @ContributesAndroidInjector(modules = [ChooseGameActivityModule::class])
    abstract fun bindChooseGameActivity(): ChooseGameActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [ChooseStageActivityModule::class])
    abstract fun bindChooseStageActivity(): ChooseStageActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [ChoosePlayersActivityModule::class])
    abstract fun bindChoosePlayersActivity(): ChoosePlayersActivity

}