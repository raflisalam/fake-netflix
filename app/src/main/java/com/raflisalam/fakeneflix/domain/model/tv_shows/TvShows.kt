package com.raflisalam.fakeneflix.domain.model.tv_shows

data class TvShows(
    val tvShowsId: Int,
    val background_poster: String,
    val release_date: String,
    val genresId: List<Int>,
    val titleTvShows: String,
    val description: String,
    val image_poster: String,
    val rating: Int
)
