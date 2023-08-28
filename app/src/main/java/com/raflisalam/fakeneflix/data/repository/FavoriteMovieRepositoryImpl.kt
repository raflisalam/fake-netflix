package com.raflisalam.fakeneflix.data.repository

import com.raflisalam.fakeneflix.data.local.FavoriteMovieDao
import com.raflisalam.fakeneflix.data.local.entity.FavoriteMovieEntity
import com.raflisalam.fakeneflix.domain.repository.FavoriteMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) : FavoriteMoviesRepository {

    override suspend fun addFavoriteMovie(movie: FavoriteMovieEntity) {
        favoriteMovieDao.addFavoriteMovie(movie)
    }

    override suspend fun removeFavoriteMovie(movie: FavoriteMovieEntity) {
        favoriteMovieDao.removeFavoriteMovie(movie)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return favoriteMovieDao.getFavoriteMovies()
    }
}