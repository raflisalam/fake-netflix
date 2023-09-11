package com.raflisalam.fakeneflix.domain.model.discover

data class DiscoverMovie(
    val genreIds: List<Int>?,
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double?
)
