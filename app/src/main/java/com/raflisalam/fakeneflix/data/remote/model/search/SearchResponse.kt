package com.raflisalam.fakeneflix.data.remote.model.search

import dagger.multibindings.IntoMap

data class SearchResponse(
    val results: List<Result>?
)

data class Result(
    val id: Int?,
    val title: String?,
    val name: String?,
    val overview: String?,
    val genre_ids: List<Int>?,
    val poster_path: String?,
    val first_air_date: String?,
    val release_date: String?,
    val vote_average: Double?,
    val media_type: String,
    val gender: Int?,
    val profile_path: String?
)
