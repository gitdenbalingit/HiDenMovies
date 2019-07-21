package com.hiden.challenge.presentation.di.modules

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.provider.Settings
import com.hiden.challenge.presentation.application.HiDenChallengeApplication
import com.hiden.challenge.presentation.common.arch.PostExecutionThread
import com.hiden.challenge.presentation.common.arch.ThreadExecutor
import com.hiden.challenge.presentation.common.arch.ThreadPoolExecutor
import com.hiden.challenge.presentation.common.arch.UIThread
import com.hiden.challenge.presentation.di.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideApplication(myChatApplication: HiDenChallengeApplication): Application = myChatApplication

    @Provides
    @PerApplication
    fun provideContext(
        myChatApplication: HiDenChallengeApplication
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

