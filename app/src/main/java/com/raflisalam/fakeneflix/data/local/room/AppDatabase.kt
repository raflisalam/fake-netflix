package com.raflisalam.fakeneflix.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raflisalam.fakeneflix.data.local.FavoriteMovieDao
import com.raflisalam.fakeneflix.data.local.entity.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
