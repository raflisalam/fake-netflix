package com.raflisalam.fakeneflix.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWatchlistMovie(movie: WatchlistMoviesEntity)

    @Delete
    suspend fun removeWatchlistMovie(movie: WatchlistMoviesEntity)

    @Query("SELECT * FROM watchlist_movies")
    fun getWatchlistMovies(): Flow<List<WatchlistMoviesEntity>>
}