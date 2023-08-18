package com.raflisalam.fakeneflix.di

import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import com.raflisalam.fakeneflix.domain.usecase.get_nowplaying.GetNowPlayingMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_nowplaying.GetNowPlayingMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_popular.GetPopularMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_popular.GetPopularMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_upcoming.GetUpcomingMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_upcoming.GetUpcomingMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetMoviesPopularUseCase(
        repository: MoviesRepository
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideGetMoviesNowPlayingUseCase(
        repository: MoviesRepository
    ): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideGetMoviesUpcomingUseCase(
        repository: MoviesRepository
    ): GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCaseImpl(repository)
    }

}