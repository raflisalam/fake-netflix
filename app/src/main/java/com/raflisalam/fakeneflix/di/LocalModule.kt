package com.raflisalam.fakeneflix.di

import com.raflisalam.fakeneflix.data.repository.FavoriteMovieRepositoryImpl
import com.raflisalam.fakeneflix.domain.repository.FavoriteMoviesRepository
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
    fun provideFavoriteMovieRepository(
        repository: FavoriteMovieRepositoryImpl
    ): FavoriteMoviesRepository {
        return repository
    }
}