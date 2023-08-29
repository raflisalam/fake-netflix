package com.raflisalam.fakeneflix.di

import com.raflisalam.fakeneflix.data.local.dao.WatchlistMoviesDao
import com.raflisalam.fakeneflix.domain.repository.WatchlistMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideWatchlistMoviesRepository(
        watchlistMoviesDao: WatchlistMoviesDao
    ): WatchlistMoviesRepository {
        return WatchlistMoviesRepository(watchlistMoviesDao)
    }
}