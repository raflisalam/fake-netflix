package com.raflisalam.fakeneflix.data.remote.model.movies

import com.raflisalam.fakeneflix.domain.model.movies.MovieDetails

data class MovieDetailsDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val videos: Videos,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailsDto.toMovieDetails() : MovieDetails {
    return MovieDetails(
        moviesId = id,
        backdrop_poster = backdrop_path,
        genres = genres,
        synopsis = overview,
        image_poster = poster_path,
        release_date = release_date,
        runtime = runtime,
        title = title,
        videos = videos,
        rating = vote_average,
        rating_vote = vote_count,
        productionCountry = production_countries,
        original_title = original_title,
        tagline = tagline
    )
}