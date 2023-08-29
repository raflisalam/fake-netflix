package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.data.local.WatchlistMoviesDao
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class WatchlistMoviesRepository @Inject constructor(
    private val watchlistMoviesDao: WatchlistMoviesDao
) {
    val watchlistMovies: Flow<List<WatchlistMoviesEntity>> = watchlistMoviesDao.getWatchlistMovies()

    suspend fun isMovieWatchlist(movieId: Int): Boolean {
        val watchlistMovies = watchlistMoviesDao.getWatchlistMovies().first()
        return watchlistMovies.any { it.id == movieId }
    }

    suspend fun addWatchlistMovies(movie: WatchlistMoviesEntity) {
        watchlistMoviesDao.addWatchlistMovie(movie)
    }

    suspend fun removeWatchlistMovies(movie: WatchlistMoviesEntity) {
        watchlistMoviesDao.removeWatchlistMovie(movie)
    }
}