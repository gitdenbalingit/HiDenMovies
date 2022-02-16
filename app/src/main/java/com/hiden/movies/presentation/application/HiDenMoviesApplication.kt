package com.hiden.movies.presentation.application

import com.hiden.movies.BuildConfig
import com.hiden.movies.data.di.NetworkModule
import com.hiden.movies.presentation.di.modules.ApplicationModule
import dagger.android.support.DaggerApplication
import timber.log.Timber


class HiDenMoviesApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // insert crash reporting tree
        }


    }

    override fun applicationInjector(): ApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule())
        .networkModule(NetworkModule())
        .build()


}