package com.hiden.movies.presentation.ui.profile

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.UserStatusItemViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
import com.hiden.movies.presentation.model.UserStatusDataView
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class UserProfileActivityModule {

    @Provides
    fun provideUserStatusItemHolderFactory(activity: UserProfileActivity): ViewHolderFactory<BindingViewHolder<UserStatusDataView>> =
            UserStatusItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: UserProfileActivity): AppCompatActivity = activity
}