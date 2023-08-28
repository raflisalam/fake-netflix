package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteMoviesRepository {
    suspend fun addFavoriteMovie(movie: FavoriteMovieEntity)
    suspend fun removeFavoriteMovie(movie: FavoriteMovieEntity)
    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
}