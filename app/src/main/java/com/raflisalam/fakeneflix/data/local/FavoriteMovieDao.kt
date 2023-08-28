package com.raflisalam.fakeneflix.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raflisalam.fakeneflix.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(movie: FavoriteMovieEntity)

    @Delete
    suspend fun removeFavoriteMovie(movie: FavoriteMovieEntity)

    @Query("SELECT * FROM favorite_movies")
    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
}