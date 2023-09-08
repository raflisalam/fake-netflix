package com.raflisalam.fakeneflix.domain.model.tv_shows

data class Season(
    val seasonId: Int?,
    val poster: String?,
    val name: String?,
    val number_season: Int?,
    val episode_count: Int?,
    val rating_season: Double?,
    val release_date: String?
)