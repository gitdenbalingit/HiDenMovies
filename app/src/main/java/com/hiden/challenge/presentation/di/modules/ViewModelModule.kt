package com.hiden.challenge.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hiden.challenge.presentation.di.ViewModelFactory
import com.hiden.challenge.presentation.di.ViewModelKey
import com.hiden.challenge.presentation.ui.choosegame.ChooseGameViewModel
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersViewModel
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

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