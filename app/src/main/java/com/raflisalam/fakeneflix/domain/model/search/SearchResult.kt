package com.raflisalam.fakeneflix.domain.model.search

data class MovieResult(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val genre_ids: List<Int>?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?
)

data class TvShowResult(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val genre_ids: List<Int>?,
    val poster_path: String?,
    val first_air_date: String?,
    val vote_average: Double?
)

data class PersonResult(
    val id: Int?,
    val name: String?,
    val gender: Int?,
    val profile_path: String?
)