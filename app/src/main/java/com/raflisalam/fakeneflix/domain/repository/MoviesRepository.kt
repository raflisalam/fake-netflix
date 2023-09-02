package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.data.remote.model.movies.MovieDetailsDto
import com.raflisalam.fakeneflix.domain.model.credits.CastMovies
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getNowPlayingMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getUpcomingMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getTopRatedMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getDetailsMovieById(movieId: Int): MovieDetailsDto

    suspend fun getRecommendationsMovies(movieId: Int, page: Int): Flow<Status<List<Movies>>>

    suspend fun getCreditsActorById(movieId: Int): Flow<Status<List<CastMovies>>>

    suspend fun getMoviesByName(moviesName: String): Flow<Status<List<Movies>>>

    suspend fun getTrendingMovies(timePeriod: String): Flow<Status<List<Movies>>>
}