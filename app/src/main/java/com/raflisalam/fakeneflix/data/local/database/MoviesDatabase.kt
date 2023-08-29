package com.raflisalam.fakeneflix.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raflisalam.fakeneflix.data.local.dao.WatchlistMoviesDao
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity

@Database(entities = [WatchlistMoviesEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun watchlistMovieDao(): WatchlistMoviesDao
}