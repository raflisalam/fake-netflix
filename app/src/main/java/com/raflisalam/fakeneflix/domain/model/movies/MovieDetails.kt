package com.raflisalam.fakeneflix.domain.model.movies

import com.raflisalam.fakeneflix.data.remote.model.movies.Genre
import com.raflisalam.fakeneflix.data.remote.model.movies.ProductionCountry
import com.raflisalam.fakeneflix.data.remote.model.movies.Videos

data class MovieDetails(
    val moviesId: Int,
    val backdrop_poster: String,
    val genres: List<Genre>,
    val synopsis: String,
    val image_poster: String,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val videos: Videos,
    val rating: Double,
    val rating_vote: Int,
    val productionCountry: List<ProductionCountry>,
    val original_title: String,
    val tagline: String
)

