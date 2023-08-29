package com.raflisalam.fakeneflix.di

import android.content.Context
import androidx.room.Room
import com.raflisalam.fakeneflix.data.local.WatchlistMoviesDao
import com.raflisalam.fakeneflix.data.local.room.AppDatabase
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideFavoriteMovieDao(appDatabase: AppDatabase): WatchlistMoviesDao {
        return appDatabase.favoriteMovieDao()
    }
}