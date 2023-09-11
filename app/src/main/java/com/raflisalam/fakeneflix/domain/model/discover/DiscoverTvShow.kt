package com.raflisalam.fakeneflix.domain.model.discover

data class DiscoverTvShow(
    val firstAirDate: String?,
    val genreIds: List<Int>?,
    val id: Int?,
    val name: String?,
    val overview: String?,
    val posterPath: String?,
    val voteAverage: Double?
)