package com.hiden.movies.presentation.ui.profile

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class UserProfileActivityModule {

//    @Provides
//    fun provideMovieItemHolderFactory(activity: MainActivity): ViewHolderFactory<BindingViewHolder<MovieDataView>> =
//            MovieItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: UserProfileActivity): AppCompatActivity = activity
}