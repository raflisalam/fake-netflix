package com.raflisalam.fakeneflix.di

import com.raflisalam.fakeneflix.data.remote.services.MoviesApi
import com.raflisalam.fakeneflix.data.repository.ActorsRepositoryImpl
import com.raflisalam.fakeneflix.data.repository.MoviesRepositoryImpl
import com.raflisalam.fakeneflix.domain.repository.ActorsRepository
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideActorsRepository(apiService: MoviesApi): ActorsRepository {
        return ActorsRepositoryImpl(apiService)
    }

}