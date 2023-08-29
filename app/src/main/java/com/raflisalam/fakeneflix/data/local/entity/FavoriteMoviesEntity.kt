package com.raflisalam.fakeneflix.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlist_movies")
data class WatchlistMoviesEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String,
    val description: String,
    val release_date: String,
    val rating: Double
)