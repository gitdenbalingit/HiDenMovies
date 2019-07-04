package com.hiden.movies.data.di

import com.hiden.movies.data.source.repository.MoviesRepository
import com.hiden.movies.data.source.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindMovieRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository

}