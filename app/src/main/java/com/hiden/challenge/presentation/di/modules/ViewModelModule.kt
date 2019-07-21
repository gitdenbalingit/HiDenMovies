package com.hiden.challenge.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hiden.challenge.presentation.di.ViewModelFactory
import com.hiden.challenge.presentation.di.ViewModelKey
import com.hiden.challenge.presentation.ui.choosegame.ChooseGameViewModel
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersViewModel
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageViewModel
import com.hiden.challenge.presentation.ui.detail.DetailActivityViewModel
import com.hiden.challenge.presentation.ui.main.MainActivityViewModel
import com.hiden.challenge.presentation.ui.search.SearchMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchMovieViewModel::class)
    abstract fun bindSearchMovieViewModel(viewModel: SearchMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    abstract fun bindDetailActivityViewModel(viewModel: DetailActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseGameViewModel::class)
    abstract fun bindChooseGameViewModel(viewModel: ChooseGameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChooseStageViewModel::class)
    abstract fun bindChooseStageViewModel(viewModel: ChooseStageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChoosePlayersViewModel::class)
    abstract fun bindChoosePlayersViewModel(viewModel: ChoosePlayersViewModel): ViewModel


}