package com.hiden.challenge.data.di

import com.hiden.challenge.data.source.repository.MoviesRepository
import com.hiden.challenge.data.source.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindMovieRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository

}