package com.raflisalam.fakeneflix.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.raflisalam.fakeneflix.data.local.WatchlistMoviesDao
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity

@Database(entities = [WatchlistMoviesEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): WatchlistMoviesDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                .addMigrations(migration_1_2) // Menggunakan kelas migrasi yang telah Anda definisikan
                .build()
        }
    }
}
