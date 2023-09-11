package com.raflisalam.fakeneflix.data.remote.model.discover

data class DiscoverMovieResponse(
    val results: List<DiscoverMovieNetwork>
)

data class DiscoverMovieNetwork(
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)