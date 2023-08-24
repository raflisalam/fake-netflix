package com.raflisalam.fakeneflix.di

import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import com.raflisalam.fakeneflix.domain.usecase.background.ChangeBackgroundLayoutUseCase
import com.raflisalam.fakeneflix.domain.usecase.background.ChangeBackgroundLayoutUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_details_movie.get_credits_movie.GetCreditsMovieUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_details_movie.get_credits_movie.GetCreditsMovieUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_nowplaying.GetNowPlayingMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_nowplaying.GetNowPlayingMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_popular.GetPopularMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_popular.GetPopularMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_toprated.GetTopRatedMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_toprated.GetTopRatedMoviesUseCaseImpl
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

    @Provides
    fun provideGetMoviesTopRatedUseCase(
        repository: MoviesRepository
    ): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideGetCreditsMovies(
        repository: MoviesRepository
    ): GetCreditsMovieUseCase {
        return GetCreditsMovieUseCaseImpl(repository)
    }

    @Provides
    fun provideBackgroundLayoutUseCase(
        repository: MoviesRepository
    ): ChangeBackgroundLayoutUseCase {
        return ChangeBackgroundLayoutUseCaseImpl(repository)
    }

}