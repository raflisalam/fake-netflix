package com.raflisalam.fakeneflix.domain.usecase.favorite_movies

import com.raflisalam.fakeneflix.data.local.entity.FavoriteMovieEntity
import com.raflisalam.fakeneflix.domain.repository.FavoriteMoviesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class FavoriteMoviesUseCase @Inject constructor(
    private val repository: FavoriteMoviesRepository
) {
    suspend fun addFavoriteMovie(movie: FavoriteMovieEntity) {
        repository.addFavoriteMovie(movie)
    }

    suspend fun removeFavoriteMovie(movie: FavoriteMovieEntity) {
        repository.removeFavoriteMovie(movie)
    }

    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return repository.getFavoriteMovies()
    }
}