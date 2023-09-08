package com.raflisalam.fakeneflix.domain.model.tv_shows

import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.Credits
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.Genre
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.SeasonDto

data class TvShowsDetail(
    val seriesId: Int?,
    val background_poster: String?,
    val release_date: String?,
    val genresId: List<Genre>?,
    val titleTvShows: String?,
    val overview: String?,
    val image_poster: String?,
    val rating: Double?,
    val seasons: List<Season>?,
    val original_name: String?,
    val tagline: String?,
    val credits: Credits?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?
)
