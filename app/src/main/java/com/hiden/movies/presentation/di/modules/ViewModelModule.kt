package com.hiden.movies.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hiden.movies.presentation.di.ViewModelFactory
import com.hiden.movies.presentation.di.ViewModelKey
import com.hiden.movies.presentation.ui.detail.DetailActivityViewModel
import com.hiden.movies.presentation.ui.main.MainActivityViewModel
import com.hiden.movies.presentation.ui.profile.UserProfileViewModel
import com.hiden.movies.presentation.ui.search.SearchMovieViewModel
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
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindUserProfileViewModel(viewModel: UserProfileViewModel): ViewModel


}