package com.raflisalam.fakeneflix.domain.model.movies

data class WatchlistMovies(
    val id: Int,
    val title: String,
    val image_poster: String,
    val description: String,
    val release_date: String,
    val rating: Double
)