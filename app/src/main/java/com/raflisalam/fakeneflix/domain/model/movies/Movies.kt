package com.raflisalam.fakeneflix.domain.model.movies

data class Movies(
    val id: Int,
    val title: String,
    val poster: String,
    val background: String,
    val description: String,
    val release_date: String,
    val rating: Double
)
