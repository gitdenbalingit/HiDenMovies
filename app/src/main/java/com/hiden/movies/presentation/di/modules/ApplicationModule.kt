package com.hiden.movies.presentation.di.modules

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.provider.Settings
import com.hiden.movies.presentation.application.HiDenMoviesApplication
import com.hiden.movies.presentation.common.arch.PostExecutionThread
import com.hiden.movies.presentation.common.arch.ThreadExecutor
import com.hiden.movies.presentation.common.arch.ThreadPoolExecutor
import com.hiden.movies.presentation.common.arch.UIThread
import com.hiden.movies.presentation.di.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideApplication(myChatApplication: HiDenMoviesApplication): Application = myChatApplication

    @Provides
    @PerApplication
    fun provideContext(
        myChatApplication: HiDenMoviesApplication
    ): Context = myChatApplication.applicationContext


    @SuppressLint("HardwareIds")
    @Provides
    @Named(Settings.Secure.ANDROID_ID)
    @PerApplication
    internal fun provideDeviceId(context: Context) =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    @Provides
    @PerApplication
    fun provideLocalBroadcastManager(context: Context) =
        androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(context)

    @Provides
    @PerApplication
    fun provideSharedPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: ThreadPoolExecutor): ThreadExecutor = jobExecutor

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread
}

