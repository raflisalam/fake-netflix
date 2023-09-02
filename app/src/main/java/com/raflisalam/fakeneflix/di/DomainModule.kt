package com.raflisalam.fakeneflix.di

import com.raflisalam.fakeneflix.domain.repository.ActorsRepository
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import com.raflisalam.fakeneflix.domain.repository.TvShowsRepository
import com.raflisalam.fakeneflix.domain.usecase.background.ChangeBackgroundLayoutUseCase
import com.raflisalam.fakeneflix.domain.usecase.background.ChangeBackgroundLayoutUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_detail_actors.get_movie_credits_actors.GetMovieCreditsActorsUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_detail_actors.get_movie_credits_actors.GetMovieCreditsActorsUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_credits_movie.GetCreditsMovieUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_credits_movie.GetCreditsMovieUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_recommendations.GetRecommendationsMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_recommendations.GetRecommendationsMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_nowplaying.GetNowPlayingMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_nowplaying.GetNowPlayingMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_popular.GetPopularMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_popular.GetPopularMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_popular_actors.GetPopularActorsUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_popular_actors.GetPopularActorsUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_toprated.GetTopRatedMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_toprated.GetTopRatedMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_trending.GetTrendingMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_trending.GetTrendingMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.get_upcoming.GetUpcomingMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_upcoming.GetUpcomingMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.search.SearchMoviesUseCase
import com.raflisalam.fakeneflix.domain.usecase.search.SearchMoviesUseCaseImpl
import com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_popular.GetPopularTvShowsUseCase
import com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_popular.GetPopularTvShowsUseCaseImpl
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
    fun provideGetTrendingMoviesUseCase(
        repository: MoviesRepository
    ): GetTrendingMoviesUseCase {
        return GetTrendingMoviesUseCaseImpl(repository)
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

    @Provides
    fun provideSearchMoviesUseCase(
        repository: MoviesRepository
    ): SearchMoviesUseCase {
        return SearchMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideGetRecommendationsMoviesUseCase(
        repository: MoviesRepository
    ): GetRecommendationsMoviesUseCase {
        return GetRecommendationsMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideGetPopularActorsUseCase(
        repository: ActorsRepository
    ): GetPopularActorsUseCase {
        return GetPopularActorsUseCaseImpl(repository)
    }

    @Provides
    fun provideGetMovieCreditsActorsUseCase(
        repository: ActorsRepository
    ): GetMovieCreditsActorsUseCase {
        return GetMovieCreditsActorsUseCaseImpl(repository)
    }

    @Provides
    fun provideGetPopularTvShowsUseCase(
        repository: TvShowsRepository
    ): GetPopularTvShowsUseCase {
        return GetPopularTvShowsUseCaseImpl(repository)
    }

}