package com.raflisalam.fakeneflix.data.remote.model.discover

data class DiscoverTvShowResponse(
    val results: List<DiscoverTvShowNetwork>
)

data class DiscoverTvShowNetwork(
    val backdrop_path: String?,
    val first_air_date: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val name: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val vote_average: Double?,
    val vote_count: Int?
)