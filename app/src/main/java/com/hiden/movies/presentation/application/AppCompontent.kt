package com.hiden.movies.presentation.application

import com.hiden.movies.data.di.DataModule
import com.hiden.movies.data.di.NetworkModule
import com.hiden.movies.presentation.di.PerApplication
import com.hiden.movies.presentation.di.modules.ActivityBindingModule
import com.hiden.movies.presentation.di.modules.ApplicationModule
import com.hiden.movies.presentation.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        (ApplicationModule::class),
        (NetworkModule::class),
        (DataModule::class),
        (ActivityBindingModule::class),
        (ViewModelModule::class),
        (AndroidInjectionModule::class),
        (AndroidSupportInjectionModule::class)]
)
@PerApplication
interface ApplicationComponent: AndroidInjector<HiDenMoviesApplication>
