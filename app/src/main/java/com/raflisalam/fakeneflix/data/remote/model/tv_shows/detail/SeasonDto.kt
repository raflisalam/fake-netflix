package com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail

import com.raflisalam.fakeneflix.domain.model.tv_shows.Season

data class SeasonDto(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Double
)

fun SeasonDto.toSeason(): Season {
    return Season(
        seasonId = id,
        name = name,
        episode_count = episode_count,
        poster = poster_path,
        number_season = season_number,
        rating_season = vote_average,
        release_date = air_date
    )
}