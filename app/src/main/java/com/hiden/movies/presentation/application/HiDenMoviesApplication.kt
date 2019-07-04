package com.hiden.movies.presentation.application

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.BroadcastReceiver
import com.hiden.movies.BuildConfig
import com.hiden.movies.data.di.NetworkModule
import dagger.android.*
import timber.log.Timber
import javax.inject.Inject


class HiDenMoviesApplication : Application(), HasActivityInjector, HasBroadcastReceiverInjector,
    HasServiceInjector {

    @Inject protected lateinit var broadcastReceiverDispatchingAndroidInjector: DispatchingAndroidInjector<BroadcastReceiver>
    @Inject protected lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>
    @Inject protected lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    val appComponent by lazy { initializeAppComponent() }


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // insert crash reporting tree
        }

        appComponent.inject(this)

    }


    open fun initializeAppComponent(): ApplicationComponent = DaggerApplicationComponent.builder()
        .application(this)
        .networkModule(NetworkModule())
        .build()


    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> =
        broadcastReceiverDispatchingAndroidInjector

    override fun serviceInjector(): AndroidInjector<Service> = serviceDispatchingAndroidInjector


}