package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.data.remote.model.MovieDetailsDto
import com.raflisalam.fakeneflix.data.remote.model.credits.MovieCredits
import com.raflisalam.fakeneflix.domain.model.Actor
import com.raflisalam.fakeneflix.domain.model.MovieDetails
import com.raflisalam.fakeneflix.domain.model.Movies
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getNowPlayingMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getUpcomingMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getTopRatedMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getDetailsMovieById(movieId: Int): MovieDetailsDto

    suspend fun getCreditsActorById(movieId: Int): Flow<Status<List<Actor>>>

    suspend fun getMoviesByName(moviesName: String): Flow<Status<List<Movies>>>

    suspend fun getTrendingMovies(timePeriod: String): Flow<Status<List<Movies>>>
}