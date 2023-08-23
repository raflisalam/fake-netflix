package com.raflisalam.fakeneflix.domain.model

import com.raflisalam.fakeneflix.data.remote.model.Genre
import com.raflisalam.fakeneflix.data.remote.model.Videos

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
    val rating_vote: Int
)

