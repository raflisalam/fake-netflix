package com.raflisalam.fakeneflix.domain.usecase.get_watchlist

import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity
import com.raflisalam.fakeneflix.domain.model.movies.WatchlistMovies
import com.raflisalam.fakeneflix.domain.repository.WatchlistMoviesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class WatchlistMoviesUseCase @Inject constructor(
    private val repository: WatchlistMoviesRepository
) {
    suspend operator fun invoke(movie: WatchlistMovies) {
        val watchlistMovies = WatchlistMoviesEntity(
            movie.id,
            movie.title,
            movie.image_poster,
            movie.description,
            movie.release_date,
            movie.rating
        )
        if (repository.isMovieWatchlist(movie.id)) {
            repository.removeWatchlistMovies(watchlistMovies)
        } else {
            repository.addWatchlistMovies(watchlistMovies)
        }
    }


    suspend fun deleteFromWatchlist(movieId: Int) {
        repository.deleteWatchlistMovies(movieId)
    }

    fun execute(): Flow<List<WatchlistMoviesEntity>> {
        return repository.watchlistMovies
    }
}