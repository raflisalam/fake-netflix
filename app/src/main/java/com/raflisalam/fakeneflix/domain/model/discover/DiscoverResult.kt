package com.raflisalam.fakeneflix.domain.model.discover

import com.raflisalam.fakeneflix.common.enums.MediaType

data class DiscoverResult(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val genre_ids: List<Int>?,
    val poster_path: String?,
    val first_air_date: String?,
    val release_date: String?,
    val vote_average: Double?,
    val media_type: MediaType
)
