package com.hiden.challenge.presentation.di.modules

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.hiden.challenge.presentation.di.PerActivity
import dagger.Binds
import dagger.Module

@Module
abstract class BaseActivityModule {

    @Binds
    @PerActivity
    abstract fun activity(appCompatActivity: AppCompatActivity): Activity

}