package com.raflisalam.fakeneflix.domain.model.tv_shows

data class TvShows(
    val seriesId: Int?,
    val titleTvShows: String?,
    val background_poster: String?,
    val release_date: String?,
    val genresId: List<Int>?,
    val image_poster :String?,
    val description: String?,
    val rating: Int?
)