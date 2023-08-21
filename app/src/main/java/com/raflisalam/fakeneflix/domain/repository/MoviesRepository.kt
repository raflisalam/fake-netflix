package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.Movies
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getNowPlayingMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getUpcomingMovies(page: Int): Flow<Status<List<Movies>>>

    suspend fun getTopRatedMovies(page: Int): Flow<Status<List<Movies>>>
}