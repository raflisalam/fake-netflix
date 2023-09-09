package com.raflisalam.fakeneflix.domain.model.tv_shows

import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.Credits
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.Genre
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.ProductionCountry
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.SeasonDto
import com.raflisalam.fakeneflix.domain.model.credits.CreditsTvShow

data class TvShowsDetail(
    val seriesId: Int?,
    val background_poster: String?,
    val createdBy: List<CreatedBy>?,
    val release_date: String?,
    val genresId: List<Genre>?,
    val titleTvShows: String?,
    val overview: String?,
    val image_poster: String?,
    val rating: Double?,
    val seasons: List<Season>?,
    val original_name: String?,
    val tagline: String?,
    val credits: CreditsTvShow?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val production_countries: List<ProductionCountry>?,
)
