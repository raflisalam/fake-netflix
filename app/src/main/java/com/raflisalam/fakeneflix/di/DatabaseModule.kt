package com.raflisalam.fakeneflix.di

import android.content.Context
import androidx.room.Room
import com.raflisalam.fakeneflix.data.local.dao.WatchlistMoviesDao
import com.raflisalam.fakeneflix.data.local.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java,
            "movies_database"
        ).build()
    }

    @Provides
    fun provideFavoriteMovieDao(appDatabase: MoviesDatabase): WatchlistMoviesDao {
        return appDatabase.watchlistMovieDao()
    }
}