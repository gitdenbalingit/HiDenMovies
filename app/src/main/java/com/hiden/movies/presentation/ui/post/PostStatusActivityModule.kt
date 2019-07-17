package com.hiden.movies.presentation.ui.post

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class PostStatusActivityModule {



    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: PostStatusActivity): AppCompatActivity = activity
}