package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.data.local.dao.WatchlistMoviesDao
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


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