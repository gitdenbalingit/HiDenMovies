package com.hiden.challenge.presentation.di.modules

import com.hiden.challenge.presentation.di.PerActivity
import com.hiden.challenge.presentation.ui.choosegame.ChooseGameActivity
import com.hiden.challenge.presentation.ui.choosegame.ChooseGameActivityModule
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersActivity
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersActivityModule
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageActivity
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageActivityModule
import com.hiden.challenge.presentation.ui.detail.DetailActivity
import com.hiden.challenge.presentation.ui.detail.DetailActivityModule
import com.hiden.challenge.presentation.ui.main.MainActivity
import com.hiden.challenge.presentation.ui.main.MainActivityModule
import com.hiden.challenge.presentation.ui.search.SearchMovieActivity
import com.hiden.challenge.presentation.ui.search.SearchMovieActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [SearchMovieActivityModule::class])
    abstract fun bindSearchMovieActivity(): SearchMovieActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun bindDetailActivity(): DetailActivity

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