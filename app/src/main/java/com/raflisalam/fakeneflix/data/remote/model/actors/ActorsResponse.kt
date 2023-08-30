package com.raflisalam.fakeneflix.data.remote.model.actors

data class ActorsResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)