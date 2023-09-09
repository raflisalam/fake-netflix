package com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail

import com.raflisalam.fakeneflix.domain.model.credits.CreditsTvShow
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShowsDetail

data class TvShowsDetailsDto(
    val adult: Boolean,
    val backdrop_path: String,
    val created_by: List<CreatedByDto>? = null,
    val episode_run_time: List<Any>,
    val first_air_date: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val next_episode_to_air: Any,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val seasons: List<SeasonDto>,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val credits: Credits,
    val vote_average: Double,
    val vote_count: Int
)

fun TvShowsDetailsDto.toTvShowsDetail(): TvShowsDetail {
    val castList = credits.cast.map { it.toCastTvShow() }
    val created = created_by?.map { it.toCreatedBy() }
    val season = seasons.map { it.toSeason() }
    return TvShowsDetail(
        seriesId = id,
        background_poster = backdrop_path,
        release_date = first_air_date,
        genresId = genres,
        titleTvShows = name,
        overview = overview,
        image_poster = poster_path,
        rating = vote_average,
        original_name = original_name,
        tagline = tagline,
        credits = CreditsTvShow(
            cast = castList
        ),
        number_of_episodes = number_of_episodes,
        number_of_seasons = number_of_seasons,
        seasons = season,
        createdBy = created,
        production_countries = production_countries
    )
}