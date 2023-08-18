package com.raflisalam.fakeneflix.domain.model

data class Movies(
    val id: Int,
    val title: String,
    val poster: String,
    val description: String,
    val release_date: String,
    val rating: Double
)
