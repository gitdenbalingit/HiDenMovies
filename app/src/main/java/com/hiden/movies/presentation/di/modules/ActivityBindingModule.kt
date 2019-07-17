package com.hiden.movies.presentation.di.modules

import com.hiden.movies.presentation.di.PerActivity
import com.hiden.movies.presentation.ui.detail.DetailActivity
import com.hiden.movies.presentation.ui.detail.DetailActivityModule
import com.hiden.movies.presentation.ui.main.MainActivity
import com.hiden.movies.presentation.ui.main.MainActivityModule
import com.hiden.movies.presentation.ui.post.PostStatusActivity
import com.hiden.movies.presentation.ui.post.PostStatusActivityModule
import com.hiden.movies.presentation.ui.profile.UserProfileActivity
import com.hiden.movies.presentation.ui.profile.UserProfileActivityModule
import com.hiden.movies.presentation.ui.search.SearchMovieActivity
import com.hiden.movies.presentation.ui.search.SearchMovieActivityModule
import com.hiden.movies.presentation.ui.searchtweet.SearchTweetActivity
import com.hiden.movies.presentation.ui.searchtweet.SearchTweetActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [SearchMovieActivityModule::class])
    abstract fun bindSearchMovieActivity(): SearchMovieActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun bindDetailActivity(): DetailActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [UserProfileActivityModule::class])
    abstract fun bindUserProfileActivity(): UserProfileActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [SearchTweetActivityModule::class])
    abstract fun bindSearchTweetActivity(): SearchTweetActivity


    @PerActivity
    @ContributesAndroidInjector(modules = [PostStatusActivityModule::class])
    abstract fun bindPostStatusActivity(): PostStatusActivity

}