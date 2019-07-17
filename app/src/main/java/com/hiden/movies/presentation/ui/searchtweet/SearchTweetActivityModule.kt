package com.hiden.movies.presentation.ui.searchtweet

import androidx.appcompat.app.AppCompatActivity
import com.hiden.movies.presentation.common.viewholder.BindingViewHolder
import com.hiden.movies.presentation.common.viewholder.SearchTweetItemViewHolder
import com.hiden.movies.presentation.common.viewholder.ViewHolderFactory
import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.di.modules.BaseActivityModule
import com.hiden.movies.presentation.model.UserStatusDataView
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class])
class SearchTweetActivityModule {

    @Provides
    fun provideUserStatusItemHolderFactory(activity: SearchTweetActivity): ViewHolderFactory<BindingViewHolder<UserStatusDataView>> =
            SearchTweetItemViewHolder.Factory(activity)

    @PerActivity
    @Provides
    fun provideAppCompatActivity(activity: SearchTweetActivity): AppCompatActivity = activity
}